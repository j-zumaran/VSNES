package tech.zumaran.vsnes.genesisframework.response;

import org.springframework.http.HttpStatus;

import tech.zumaran.vsnes.genesisframework.GenesisEntity;

public class DeletedResponse extends SingleEntityResponse {

	public DeletedResponse(GenesisEntity entity) {
		super(HttpStatus.OK, entity.getType() + " ID " + entity.getId() + " deleted from database.", entity);
	}

}
