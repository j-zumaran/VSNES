package tech.zumaran.vsnes.genesisframework.query;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tech.zumaran.vsnes.genesisframework.GenesisEntity;

public interface FindByNameQuery<T extends GenesisEntity> {

	@Query(value = "SELECT * FROM #{#entityName} p WHERE p.name = :name", nativeQuery = true)
	Optional<T> findByName(@Param("name") String name);
	
}
