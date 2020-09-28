package tech.zumaran.vsnes.genesisframework.response;

import org.springframework.http.HttpStatus;

import tech.zumaran.vsnes.genesisframework.GenesisEntity;

public class PurgedResponse extends SingleEntityResponse {

	PurgedResponse(GenesisEntity entity) {
		super(HttpStatus.OK, entity.getType() + " ID " + entity.getId() + " purged from recycle bin.", entity);
	}

}
