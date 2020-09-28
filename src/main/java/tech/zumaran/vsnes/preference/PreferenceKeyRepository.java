package tech.zumaran.vsnes.preference;

import org.springframework.stereotype.Repository;

import tech.zumaran.genesis.GenesisRepository;
import tech.zumaran.genesis.query.FindByNameQuery;

@Repository
public interface PreferenceKeyRepository extends GenesisRepository<PreferenceKeyEntity>, FindByNameQuery<PreferenceKeyEntity> {

}
