package tech.zumaran.vsnes.context;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import tech.zumaran.genesis.context.GenesisContextRepository;
import tech.zumaran.genesis.context.GenesisContextService;
import tech.zumaran.genesis.exception.GenesisException;
import tech.zumaran.vsnes.preference.PreferenceKey;
import tech.zumaran.vsnes.preference.PreferenceKeyEntity;
import tech.zumaran.vsnes.preference.PreferenceKeyService;

public abstract class UserContextService
				<User extends UserContext, 
				Repository extends GenesisContextRepository<User>> 
		extends GenesisContextService<User, Repository> {
	
	@Autowired
	protected UserPreferenceService userPreferenceService;
	
	@Autowired 
	private PreferenceKeyService preferenceKeyService;

	@Override
	public User registerContext(long userId) throws GenesisException {
		User userContext = super.registerContext(userId);
		List<UserPreference> preferences = userPreferenceService.insertAll(defaultPreferences(), userContext);
		userContext.setUserPreferences(Set.copyOf(preferences));
		return userContext;
	}
	
	protected abstract Set<UserPreference> defaultPreferences();
	
	protected UserPreference createPreference(PreferenceKey key) {
		PreferenceKeyEntity prefKey = preferenceKeyService.findByKey(key);
		return new UserPreference(prefKey, prefKey.getDefaultValue());
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
