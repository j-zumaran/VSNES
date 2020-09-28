package tech.zumaran.vsnes.genesisframework;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GenesisRepository<Entity extends GenesisEntity> extends JpaRepository<Entity, Long> {
	
	@Query("FROM #{#entityName} e WHERE e.deleted = FALSE")
	List<Entity> findAll();
	
	@Query("FROM #{#entityName} p WHERE p.id = :id AND p.deleted = FALSE")
	Optional<Entity> findById(@Param("id") long id);
	
	//====================================RecycleBin==================================================
	
	@Query(value = "SELECT * FROM #{#entityName} p WHERE p.id = :id AND p.deleted = '1'", nativeQuery = true)
	Optional<Entity> findInRecycleBinById(Long id);
	
	@Query(value = "SELECT * FROM #{#entityName} e WHERE e.deleted = '1'", nativeQuery = true)
	List<Entity> findAllRecycleBin();

}
