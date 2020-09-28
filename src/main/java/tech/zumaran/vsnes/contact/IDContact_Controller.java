package tech.zumaran.vsnes.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tech.zumaran.genesis.GenesisController;
import tech.zumaran.genesis.exception.GenesisException;
import tech.zumaran.genesis.exception.NotFoundException;
import tech.zumaran.vsnes.category.Status;

public abstract class IDContact_Controller<C extends IDContact> extends GenesisController<C> {
	
	@Autowired
	protected IDContact_Service<C> service;
	
	@PutMapping("/update/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Status status) throws NotFoundException {
        return responseFactory.updated(service.updateStatus(id, status));
    } 
	
	@PutMapping("/update/{id}/reputation/{reputation}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @PathVariable String reputation) throws GenesisException {
        return responseFactory.updated(service.updateReputation(id, reputation));
    } 
}
