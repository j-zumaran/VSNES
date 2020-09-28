package tech.zumaran.vsnes.genesisframework;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tech.zumaran.vsnes.genesisframework.exception.GenesisException;
import tech.zumaran.vsnes.genesisframework.exception.NotFoundException;
import tech.zumaran.vsnes.genesisframework.exception.NotFoundInRecycleBin_Exception;
import tech.zumaran.vsnes.genesisframework.response.ResponseFactory;

public abstract class GenesisController
		<Entity extends GenesisEntity,
		Repository extends GenesisRepository<Entity>,
		Service extends GenesisService<Entity, Repository>> {

	@Autowired
	protected Service service;

	@Autowired
    protected ResponseFactory responseFactory;

	@GetMapping("/all")
    public ResponseEntity<List<Entity>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entity> getById(@PathVariable long id) throws NotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> insert(@RequestBody Entity entity) throws GenesisException {
    	return responseFactory.created(service.insert(entity));
    }
    
    @PostMapping("/addall")
    public ResponseEntity<?> insertAll(@RequestBody List<Entity> entities) throws GenesisException {
        return responseFactory.created(service.insertAll(entities));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Entity entity) throws NotFoundException {
        return responseFactory.updated(service.update(id, entity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) throws NotFoundException {
        return responseFactory.deleted(service.delete(id));
    }
    
    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAll(@RequestBody List<Long> ids) {
        return responseFactory.deleted(service.deleteAllById(ids));
    }
    
    @PutMapping("/recover/{id}")
    public ResponseEntity<?> recover(@PathVariable long id) throws NotFoundInRecycleBin_Exception {
        return responseFactory.recovered(service.recover(id));
    }
    
    @PutMapping("/recover/all")
    public ResponseEntity<?> recoverAll(@RequestBody List<Long> ids) {
        return responseFactory.recovered(service.recoverAllById(ids));
    }

    @DeleteMapping("/purge/{id}")
    public ResponseEntity<?> purge(@PathVariable long id) throws NotFoundInRecycleBin_Exception {
        return responseFactory.purged(service.purge(id));
    }
    
    @DeleteMapping("/purge/all")
    public ResponseEntity<?> purge(@RequestBody List<Long> ids) throws NotFoundInRecycleBin_Exception {
        return responseFactory.purged(service.purgeAllById(ids));
    }
    
    @GetMapping("/recyclebin")
    public ResponseEntity<List<Entity>> recycleBin() {
        return ResponseEntity.ok(service.recycleBin());
    }
    
    @GetMapping("/recyclebin/{id}")
    public ResponseEntity<Entity> recycleBinById(@PathVariable long id) throws NotFoundInRecycleBin_Exception {
        return ResponseEntity.ok(service.findInRecycleBinById(id));
    }
}
