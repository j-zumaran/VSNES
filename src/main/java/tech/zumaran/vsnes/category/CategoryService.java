package tech.zumaran.vsnes.category;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import tech.zumaran.genesis.constraint.UniqueConstraint;
import tech.zumaran.genesis.exception.GenesisException;
import tech.zumaran.vsnes.code.CodeEntityService;

public abstract class CategoryService<C extends Category> extends CodeEntityService<C> implements UniqueConstraint<C> {

	@Autowired
	protected CategoryRepository<C> repository;
	
	@Override
	public C insert(C category) throws GenesisException {
		return uniqueInsert(category, super::insert);
	}

	@Override
	public List<C> insertAll(Collection<C> categories) throws GenesisException {
		return uniqueInsertAll(categories, super::insertAll);
	}
	
	@Override
	public Optional<C> findDuplicateEntry(C entity) {
		return repository.findByName(entity.getName());
	}
	
	@Override
	public void flushRepository() {
		repository.flush();
	}
	
}

