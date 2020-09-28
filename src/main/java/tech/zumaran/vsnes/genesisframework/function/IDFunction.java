package tech.zumaran.vsnes.genesisframework.function;

import tech.zumaran.vsnes.genesisframework.GenesisEntity;
import tech.zumaran.vsnes.genesisframework.exception.GenesisException;

public interface IDFunction<Entity extends GenesisEntity> {
	Entity apply(long id) throws GenesisException;
}
