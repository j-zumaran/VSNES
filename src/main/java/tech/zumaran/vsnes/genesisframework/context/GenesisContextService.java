package tech.zumaran.vsnes.genesisframework.context;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tech.zumaran.vsnes.genesisframework.constraint.UniqueConstraint;
import tech.zumaran.vsnes.genesisframework.exception.GenesisException;
import tech.zumaran.vsnes.genesisframework.exception.NotFoundException;

public abstract class GenesisContextService
			<Context extends GenesisContext, 
			Repository extends GenesisContextRepository<Context>> 
		implements UniqueConstraint<Context> {

	@Autowired
	protected Repository repository;
	
	protected abstract Context newContext(long userId);
	
	@Transactional(readOnly = true, noRollbackFor = NotFoundException.class)
	public Context findByContextId(long contextId) throws NotFoundException {
		Optional<Context> maybeFound = repository.findByContextId(contextId);
		if (maybeFound.isPresent()) {
			return maybeFound.get();
		} else 
			throw new NotFoundException(GenesisContext.class, contextId);
	}
	
	@Transactional(noRollbackFor = GenesisException.class)
	public Context registerContext(long userId) throws GenesisException {
		return uniqueInsert(newContext(userId), this::insertContext);
		
	}
	
	@Transactional
	private Context insertContext(Context context) {
		return repository.saveAndFlush(context);
	}

	@Override
	public void flushRepository() {
		repository.flush();
	}

	@Override
	public Optional<Context> findDuplicateEntry(Context entity) {
		return repository.findByContextId(entity.getContextId());
	}
}
