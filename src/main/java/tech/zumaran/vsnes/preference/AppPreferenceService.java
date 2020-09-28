package tech.zumaran.vsnes.preference;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppPreferenceService {

	@Autowired
	private AppPreferenceRepository repository;
	
	@Autowired 
	private PreferenceKeyService preferenceKeyService;
	
	@Transactional
	public AppPreference findByKey(PreferenceKey key) {
		PreferenceKeyEntity keyEntity = preferenceKeyService.findByKey(key);
		Optional<AppPreference> preference = repository.findByKey(keyEntity);
		if (preference.isPresent()) 
			return preference.get();
		else {
			return repository.saveAndFlush(new AppPreference(keyEntity, keyEntity.getDefaultValue()));
		}
	}
}
