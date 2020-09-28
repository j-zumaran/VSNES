package tech.zumaran.vsnes.context;

import org.springframework.stereotype.Service;

@Service
public class UserPreferenceService extends UserEntityService<UserContext, UserPreference, UserPreferenceRepository> {

	@Override
	protected Class<UserPreference> entityType() {
		return UserPreference.class;
	}

	@Override
	protected void update(UserPreference old, UserPreference updated) {
		return;
	}

}
