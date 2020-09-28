package tech.zumaran.vsnes.context;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tech.zumaran.vsnes.genesisframework.constraint.UniqueConstraint;
import tech.zumaran.vsnes.genesisframework.exception.GenesisException;
import tech.zumaran.vsnes.genesisframework.exception.NotFoundException;
import tech.zumaran.vsnes.preference.PreferenceKey;
import tech.zumaran.vsnes.preference.PreferenceKeyEntity;
import tech.zumaran.vsnes.preference.PreferenceKeyService;

public abstract class UserContextService
				<User extends UserContext, 
				Repository extends UserContextRepository<User>> 
		implements UniqueConstraint<User> {
	
	@Autowired
	protected Repository repository;
	
	protected abstract User newContext(long userId);
	
	@Autowired
	protected UserPreferenceService userPreferenceService;
	
	@Autowired 
	private PreferenceKeyService preferenceKeyService;
	
	
	@Transactional(readOnly = true, noRollbackFor = NotFoundException.class)
	public User findByContextId(long userId) throws NotFoundException {
		Optional<User> maybeFound = repository.findByUserId(userId);
		if (maybeFound.isPresent()) {
			return maybeFound.get();
		} else 
			throw new NotFoundException(UserContext.class, userId);
	}
	
	@Transactional(noRollbackFor = GenesisException.class)
	public User registerContext(long userId) throws GenesisException {
		User userContext = uniqueInsert(newContext(userId), this::insertContext);
		List<UserPreference> preferences = userPreferenceService.insertAll(defaultPreferences(), userContext);
		userContext.setUserPreferences(Set.copyOf(preferences));
		return userContext;
	}
	
	@Transactional
	private User insertContext(User context) {
		return repository.save(context);
	}
	
	protected abstract Set<UserPreference> defaultPreferences();
	
	protected UserPreference createPreference(PreferenceKey key) {
		PreferenceKeyEntity prefKey = preferenceKeyService.findByKey(key);
		return new UserPreference(prefKey, prefKey.getDefaultValue());
	}
	
	@Override
	public void flushRepository() {
		repository.flush();
	}

	@Override
	public Optional<User> findDuplicateEntry(User entity) {
		return repository.findByUserId(entity.getUserId());
	}

	/*@Transactional(readOnly = true)
	public UserPreference findPreferenceByKey(PreferenceKey key, long contextId) throws NotFoundException {
		List<UserPreference> preferences = preferenceRepository.findAllByContextId(contextId);
		Optional<UserPreference> maybePreference = preferences.stream()
				.filter(p -> p.getKey().getName().equals(key.getName()))
				.findFirst();
		
		if (maybePreference.isPresent()) 
			return maybePreference.get();
		else 
			throw new NotFoundException(AppPreference.class, key.getName());
	}*/

}
