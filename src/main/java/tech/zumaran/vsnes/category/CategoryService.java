package tech.zumaran.vsnes.category;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import tech.zumaran.vsnes.code.CodeEntityService;
import tech.zumaran.vsnes.genesisframework.constraint.UniqueConstraint;
import tech.zumaran.vsnes.genesisframework.exception.GenesisException;

public abstract class CategoryService
			<Entity extends Category, 
			Repository extends CategoryRepository<Entity>> 
		extends CodeEntityService<Entity, Repository> implements UniqueConstraint<Entity> {

	@Override
	public Entity insert(Entity category) throws GenesisException {
		return uniqueInsert(category, super::insert);
	}

	@Override
	public List<Entity> insertAll(Collection<Entity> categories) throws GenesisException {
		return uniqueInsertAll(categories, super::insertAll);
	}
	
	@Override
	public Optional<Entity> findDuplicateEntry(Entity entity) {
		return repository.findByName(entity.getName());
	}
	
	@Override
	public void flushRepository() {
		repository.flush();
	}
	
}

