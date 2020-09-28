package tech.zumaran.vsnes.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tech.zumaran.genesis.exception.GenesisException;
import tech.zumaran.genesis.exception.NotFoundException;
import tech.zumaran.vsnes.category.Status;
import tech.zumaran.vsnes.code.CodeEntityService;
import tech.zumaran.vsnes.location.LocationRepository;

public abstract class IDContact_Service<C extends IDContact> extends CodeEntityService<C> {
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Override
	public C insert(C entity) throws GenesisException {
		locationRepository.saveAndFlush(entity.getLocation());
		return super.insert(entity);
	}
	
	@Transactional(noRollbackFor = NotFoundException.class)
	public C updateStatus(long id, Status status) throws NotFoundException {
		C contact = findById(id);
		contact.setCurrentStatus(status);
		//repository.flush();
		return contact;
	}
	
	@Transactional(noRollbackFor = InvalidReputationException.class)
	public C updateReputation(long id, String reputation) throws GenesisException {
		reputation = reputation.toUpperCase();
		if (!reputation.matches(Reputation.regex()) || reputation.length() > 1) 
			throw new InvalidReputationException(reputation);
		
		C contact = findById(id);
		contact.setReputation(Reputation.valueOf(reputation));
		//repository.flush();
		return contact;
	}

}

