package tech.zumaran.vsnes.context;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.zumaran.genesis.context.GenesisContextService;
import tech.zumaran.genesis.exception.NotFoundException;
import tech.zumaran.vsnes.preference.PreferenceKey;

@Service
public class UserContextService extends GenesisContextService<UserContext> {
	
	@Autowired
	protected UserPreferenceRepository preferenceRepository;

	@Override
	protected UserContext newContext(long userId) {
		return new UserContext(userId);
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
