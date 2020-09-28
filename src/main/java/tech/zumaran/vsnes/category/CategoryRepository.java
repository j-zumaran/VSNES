package tech.zumaran.vsnes.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tech.zumaran.vsnes.genesisframework.GenesisRepository;

public interface CategoryRepository<C extends Category> extends GenesisRepository<C> {
	
	@Query(value = "SELECT * FROM category p WHERE p.deleted = '0' AND p.dtype = '#{#entityName}'", nativeQuery = true)
	List<C> findAll();
	
	@Query(value = "SELECT * FROM category p WHERE p.id = :id AND p.deleted = '0' AND p.dtype = '#{#entityName}'", nativeQuery = true)
	Optional<C> findById(@Param("id") long id);
	
	@Query(value = "SELECT * FROM category p WHERE p.name = :name AND p.dtype = '#{#entityName}'", nativeQuery = true)
	Optional<C> findByName(@Param("name") String name);
	
	//===========================================Recycle bin===========================================================
	
	@Query(value = "SELECT * FROM category p WHERE p.id = :id AND p.deleted = '1' AND p.dtype = '#{#entityName}'", nativeQuery = true)
	Optional<C> findInRecycleBinById(Long id);
	
	@Query(value = "SELECT * FROM category p WHERE p.deleted = '1' AND p.dtype = '#{#entityName}'", nativeQuery = true)
	List<C> findAllRecycleBin();
	
	
}
