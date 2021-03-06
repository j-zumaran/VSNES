package tech.zumaran.vsnes.code;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.zumaran.vsnes.genesisframework.GenesisRepository;

@Repository
public interface CodeFormatRepository extends GenesisRepository<CodeFormat> {

	@Query(value = "SELECT * FROM code_format p WHERE p.type = :type AND p.deleted = '0'", nativeQuery = true)
	Optional<CodeFormat> findByType(@Param("type") String type);
}
