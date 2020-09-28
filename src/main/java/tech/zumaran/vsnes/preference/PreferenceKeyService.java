package tech.zumaran.vsnes.preference;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PreferenceKeyService {
	
	@Autowired
	private PreferenceKeyRepository repository;
	
	@Transactional
	public PreferenceKeyEntity findByKey(PreferenceKey key) {
		Optional<PreferenceKeyEntity> pref = repository.findByName(key.getName());
		if (pref.isPresent()) 
			return pref.get();
		else {
			return repository.saveAndFlush(new PreferenceKeyEntity(key));
		}
	}
}
