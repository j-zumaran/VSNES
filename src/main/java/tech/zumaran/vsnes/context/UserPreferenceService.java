package tech.zumaran.vsnes.context;

import org.springframework.stereotype.Service;

import tech.zumaran.genesis.context.ContextEntityService;

@Service
public class UserPreferenceService extends ContextEntityService<UserContext, UserPreference> {

	@Override
	protected Class<UserPreference> entityType() {
		return UserPreference.class;
	}

	@Override
	protected void update(UserPreference old, UserPreference updated) {
		// TODO Auto-generated method stub
		
	}

}
