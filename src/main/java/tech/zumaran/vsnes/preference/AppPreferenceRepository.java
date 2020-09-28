package tech.zumaran.vsnes.preference;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tech.zumaran.genesis.GenesisRepository;

public interface AppPreferenceRepository extends GenesisRepository<AppPreference> {

	@Query("FROM AppPreference p WHERE p.key = :key AND p.deleted = 0")
	Optional<AppPreference> findByKey(@Param("key") PreferenceKeyEntity key);

}
