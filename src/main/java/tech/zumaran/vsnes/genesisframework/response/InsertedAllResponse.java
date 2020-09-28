package tech.zumaran.vsnes.genesisframework.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import tech.zumaran.vsnes.genesisframework.GenesisEntity;

public class InsertedAllResponse extends ListEntityResponse {

	public InsertedAllResponse(List<? extends GenesisEntity> entities) {
		super(HttpStatus.CREATED, "List inserted to database.", entities);
	}

}
