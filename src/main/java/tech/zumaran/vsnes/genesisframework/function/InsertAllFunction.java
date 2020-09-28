package tech.zumaran.vsnes.genesisframework.function;

import java.util.Collection;
import java.util.List;

import tech.zumaran.vsnes.genesisframework.GenesisEntity;
import tech.zumaran.vsnes.genesisframework.exception.GenesisException;


public interface InsertAllFunction<E extends GenesisEntity> {
	List<E> apply(Collection<E> all) throws GenesisException;
}
