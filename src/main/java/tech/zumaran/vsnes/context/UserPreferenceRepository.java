package tech.zumaran.vsnes.context;

import org.springframework.stereotype.Repository;

import tech.zumaran.vsnes.genesisframework.context.ContextEntityRepository;

@Repository
public interface UserPreferenceRepository extends ContextEntityRepository<UserContext, UserPreference> {

}
