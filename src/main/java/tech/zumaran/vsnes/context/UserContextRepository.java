package tech.zumaran.vsnes.context;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tech.zumaran.vsnes.genesisframework.GenesisRepository;

public interface UserContextRepository<Context extends UserContext> extends GenesisRepository<Context> {

    @Query(value = "SELECT * FROM user_context p WHERE p.user_id = :userId", nativeQuery = true)
    Optional<Context> findByUserId(@Param("userId") long userId);

}
