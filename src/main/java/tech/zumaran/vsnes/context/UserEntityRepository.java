package tech.zumaran.vsnes.context;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tech.zumaran.vsnes.genesisframework.GenesisRepository;

public interface UserEntityRepository<Context extends UserContext, Entity extends UserEntity<Context>> extends GenesisRepository<Entity> {
	
	@Query(value = "SELECT * FROM #{#entityName} p WHERE p.context_id = :contextId AND p.deleted = '0'", nativeQuery = true)
	List<Entity> findAllByContextId(@Param("contextId") long contextId);
	
	@Query(value = "SELECT * FROM #{#entityName} p WHERE p.id = :id AND p.context_id = :contextId AND p.deleted = '0'", nativeQuery = true)
	Optional<Entity> findByIdAndContextId(@Param("id") long id, @Param("contextId") long contextId);
	
	
	@Query(value = "SELECT * FROM #{#entityName} e WHERE e.context_id = :contextId AND e.deleted = '1'", nativeQuery = true)
	List<Entity> findAllRecycleBin(@Param("contextId") long contextId);
	
	@Query(value = "SELECT * FROM #{#entityName} e WHERE e.id = :id AND e.context_id = :contextId AND e.deleted = '1'", nativeQuery = true)
	Optional<Entity> findInRecycleBinById(@Param("id") long id, @Param("contextId") long contextId);
}
