package tech.zumaran.vsnes.preference;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.zumaran.vsnes.genesisframework.GenesisRepository;

@Repository
public interface AppPreferenceRepository extends GenesisRepository<AppPreference> {

	@Query("FROM AppPreference p WHERE p.key = :key AND p.deleted = 0")
	Optional<AppPreference> findByKey(@Param("key") PreferenceKeyEntity key);

}
