package tech.zumaran.vsnes.preference;

import org.springframework.stereotype.Repository;

import tech.zumaran.vsnes.genesisframework.GenesisRepository;
import tech.zumaran.vsnes.genesisframework.query.FindByNameQuery;

@Repository
public interface PreferenceKeyRepository extends GenesisRepository<PreferenceKeyEntity>, FindByNameQuery<PreferenceKeyEntity> {

}
