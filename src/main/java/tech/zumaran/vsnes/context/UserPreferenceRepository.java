package tech.zumaran.vsnes.context;

import org.springframework.stereotype.Repository;

import tech.zumaran.genesis.context.ContextEntityRepository;

@Repository
public interface UserPreferenceRepository extends ContextEntityRepository<UserContext, UserPreference> {

}
