package tech.zumaran.vsnes.genesisframework.response;

import org.springframework.http.HttpStatus;

import tech.zumaran.vsnes.genesisframework.GenesisEntity;

public class RecoveredResponse extends SingleEntityResponse {

	RecoveredResponse(GenesisEntity entity) {
		super(HttpStatus.OK, entity.getType() + " recovered ID: " + entity.getId(), entity);
	}

}
