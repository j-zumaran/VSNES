package tech.zumaran.vsnes.genesisframework.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import tech.zumaran.vsnes.genesisframework.GenesisEntity;

public class DeletedAllResponse extends ListEntityResponse {

	public DeletedAllResponse(List<? extends GenesisEntity> entities) {
		super(HttpStatus.OK, "list deleted from database", entities);
	}

}
