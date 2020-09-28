package tech.zumaran.vsnes.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tech.zumaran.vsnes.category.Status;
import tech.zumaran.vsnes.code.CodeEntityService;
import tech.zumaran.vsnes.genesisframework.GenesisRepository;
import tech.zumaran.vsnes.genesisframework.exception.GenesisException;
import tech.zumaran.vsnes.genesisframework.exception.NotFoundException;
import tech.zumaran.vsnes.location.LocationRepository;

public abstract class IDContact_Service
			<ID extends IDContact,
			Repository extends GenesisRepository<ID>> 
		extends CodeEntityService<ID, Repository> {
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Override
	public ID insert(ID entity) throws GenesisException {
		locationRepository.saveAndFlush(entity.getLocation());
		return super.insert(entity);
	}
	
	@Transactional(noRollbackFor = NotFoundException.class)
	public ID updateStatus(long id, Status status) throws NotFoundException {
		ID contact = findById(id);
		contact.setCurrentStatus(status);
		//repository.flush();
		return contact;
	}
	
	@Transactional(noRollbackFor = GenesisException.class)
	public ID updateReputation(long id, String reputation) throws GenesisException {
		reputation = reputation.toUpperCase();
		if (!reputation.matches(Reputation.regex()) || reputation.length() > 1) 
			throw new InvalidReputationException(reputation);
		
		ID contact = findById(id);
		contact.setReputation(Reputation.valueOf(reputation));
		//repository.flush();
		return contact;
	}

}

