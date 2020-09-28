package tech.zumaran.vsnes.contact;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tech.zumaran.vsnes.category.Status;
import tech.zumaran.vsnes.genesisframework.GenesisController;
import tech.zumaran.vsnes.genesisframework.GenesisRepository;
import tech.zumaran.vsnes.genesisframework.exception.GenesisException;
import tech.zumaran.vsnes.genesisframework.exception.NotFoundException;

public abstract class IDContact_Controller
				<ID extends IDContact,
				Repository extends GenesisRepository<ID>,
				Service extends IDContact_Service<ID, Repository>> 
		extends GenesisController<ID, Repository, Service> {
	
	@PutMapping("/update/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Status status) throws NotFoundException {
        return responseFactory.updated(service.updateStatus(id, status));
    } 
	
	@PutMapping("/update/{id}/reputation/{reputation}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @PathVariable String reputation) throws GenesisException {
        return responseFactory.updated(service.updateReputation(id, reputation));
    } 
}
