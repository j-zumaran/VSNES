package tech.zumaran.vsnes.context;

import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferenceRepository extends UserEntityRepository<UserContext, UserPreference> {

}
