package tech.zumaran.vsnes.genesisframework.context;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tech.zumaran.vsnes.genesisframework.GenesisRepository;

public interface GenesisContextRepository<Context extends GenesisContext> extends GenesisRepository<Context> {

    @Query(value = "SELECT * FROM #{#entityName} p WHERE p.context_id = :contextId", nativeQuery = true)
    Optional<Context> findByContextId(@Param("contextId") long contextId);

}
