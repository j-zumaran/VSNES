package tech.zumaran.vsnes.genesisframework.function;

import tech.zumaran.vsnes.genesisframework.GenesisEntity;
import tech.zumaran.vsnes.genesisframework.exception.GenesisException;

public interface InsertFunction<E extends GenesisEntity> {
	E apply(E entity) throws GenesisException;
}
