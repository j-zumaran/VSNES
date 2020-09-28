package tech.zumaran.vsnes.contact;

import tech.zumaran.vsnes.genesisframework.exception.GenesisException;

public class InvalidReputationException extends GenesisException {

	private static final long serialVersionUID = -4714454417294579557L;
	
	public InvalidReputationException(String reputation) {
		super("Invalid reputation", reputation);
	}

}
