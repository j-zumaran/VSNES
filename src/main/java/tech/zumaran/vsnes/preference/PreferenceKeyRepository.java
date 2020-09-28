package tech.zumaran.vsnes.preference;

import tech.zumaran.genesis.GenesisRepository;
import tech.zumaran.genesis.query.FindByNameQuery;

public interface PreferenceKeyRepository extends GenesisRepository<PreferenceKeyEntity>, FindByNameQuery<PreferenceKeyEntity> {

}
