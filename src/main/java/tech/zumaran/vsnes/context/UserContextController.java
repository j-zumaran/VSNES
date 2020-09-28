package tech.zumaran.vsnes.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import tech.zumaran.vsnes.genesisframework.exception.GenesisException;

public class UserContextController
			<Context extends UserContext, 
			Repository extends UserContextRepository<Context>,
			Service extends UserContextService<Context, Repository>> {

	@Autowired
	protected Service service;

	@PostMapping("/register/{userId}")
	public ResponseEntity<Context> registerContext(@PathVariable long userId) throws GenesisException {
		return ResponseEntity.ok(service.registerContext(userId));
	}
}
