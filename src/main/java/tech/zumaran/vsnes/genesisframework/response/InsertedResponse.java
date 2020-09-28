package tech.zumaran.vsnes.genesisframework.response;

import org.springframework.http.HttpStatus;

import tech.zumaran.vsnes.genesisframework.GenesisEntity;

public class InsertedResponse extends SingleEntityResponse {
	
	public InsertedResponse(GenesisEntity entity) {
		super(HttpStatus.CREATED, entity.getType() + " inserted to database.", entity);
	}
}
