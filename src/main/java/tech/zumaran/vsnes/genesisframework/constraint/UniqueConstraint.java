package tech.zumaran.vsnes.genesisframework.constraint;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import tech.zumaran.vsnes.genesisframework.GenesisEntity;
import tech.zumaran.vsnes.genesisframework.exception.GenesisException;
import tech.zumaran.vsnes.genesisframework.exception.UniqueConstraintException;
import tech.zumaran.vsnes.genesisframework.function.InsertAllFunction;
import tech.zumaran.vsnes.genesisframework.function.InsertFunction;

public interface UniqueConstraint
		<Entity extends GenesisEntity> {
	
	@Transactional(readOnly = true)
	Optional<Entity> findDuplicateEntry(Entity entity);
	
	@Transactional(noRollbackFor = GenesisException.class)
	default Entity uniqueInsert(Entity entity, InsertFunction<Entity> insertFunction) throws GenesisException {
		try {
			Optional<Entity> maybeDuplicate = findDuplicateEntry(entity); 
			if (maybeDuplicate.isEmpty()) {
				return insertFunction.apply(entity);
			} else {
				Entity duplicate = maybeDuplicate.get();
				if (duplicate.isDeleted()) {
					duplicate.setDeleted(false);
					return duplicate;
				} else {
					throw new UniqueConstraintException("Duplicate entity", entity.getType(), entity.toString());
				}
			}
		} catch (DataIntegrityViolationException e) {
    		final var cause = e.getMostSpecificCause();
    		
    		if (cause instanceof SQLIntegrityConstraintViolationException) {
    			final var sqlViolation = (SQLIntegrityConstraintViolationException) cause;
    			throw new UniqueConstraintException(sqlViolation.getErrorCode() + "", sqlViolation.getMessage());
    		} else {
    			throw new UniqueConstraintException(e.getMessage());
    		}
		}
	}

	@Transactional(noRollbackFor = GenesisException.class)
	default List<Entity> uniqueInsertAll(Collection<Entity> entities, InsertAllFunction<Entity> insertFunction) throws GenesisException {
		if (entities.isEmpty()) return List.of();
		
		try {
			return new ArrayList<Entity>(insertFunction.apply(entities));
		} catch (DataIntegrityViolationException e) {
    		final var cause = e.getMostSpecificCause();
    		
    		if (cause instanceof SQLIntegrityConstraintViolationException) {
    			final var sqlViolation = (SQLIntegrityConstraintViolationException) cause;
    			
    			if (sqlViolation.getErrorCode() != 1062) 
    				throw new UniqueConstraintException(sqlViolation.getErrorCode() + "", sqlViolation.getMessage());
    			
    			EntryWithDuplicates<Entity> entry = findDuplicateEntries(entities); 
				
				if (entry.newOnes.isEmpty()) 
					throw new UniqueConstraintException("All entities are duplicates");
				
				List<Entity> inserted = new ArrayList<Entity>(insertFunction.apply(entry.newOnes));
				
				entry.duplicates.stream()
					.filter(d -> d.isDeleted())
					.forEach(d -> d.setDeleted(false));
				//flushRepository();
				
				inserted.addAll(entry.duplicates);
				return inserted;
    		} else {
    			throw new UniqueConstraintException(e.getMessage());
    		}
		}
	}

	default EntryWithDuplicates<Entity> findDuplicateEntries(Collection<Entity> entities) {
		ArrayList<Entity> duplicates = new ArrayList<Entity>();
		ArrayList<Entity> newOnes = new ArrayList<Entity>();

		for (Entity entity: entities) {
			Optional<Entity> maybeDuplicate = findDuplicateEntry(entity);
			if (maybeDuplicate.isPresent()) {
				duplicates.add(maybeDuplicate.get());
			} else {
				newOnes.add(entity);
			}
		}
		return new EntryWithDuplicates<Entity>(newOnes, duplicates);
	}
	
	@AllArgsConstructor
	static class EntryWithDuplicates<E extends GenesisEntity> {
		final List<E> newOnes;
		final List<E> duplicates;
	}
}
