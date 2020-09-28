package tech.zumaran.vsnes.code;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import tech.zumaran.genesis.GenesisRepository;
import tech.zumaran.genesis.GenesisService;
import tech.zumaran.genesis.exception.GenesisException;
import tech.zumaran.vsnes.preference.AppPreference;
import tech.zumaran.vsnes.preference.AppPreferenceService;
import tech.zumaran.vsnes.preference.DefaultAppPreference;

public abstract class CodeEntityService
			<Code extends CodeEntity, 
			Repository extends GenesisRepository<Code>> 
		extends GenesisService<Code, Repository> {
	
	@Autowired
	protected CodeFormatRepository formatRepository;
	
	@Autowired
	protected AppPreferenceService appPreferenceService;
	
	@Override
	public Code insert(Code entity) throws GenesisException {
		entity.setCodeFormat(findFormat(entity));
		return super.insert(entity);
	}
	
	public List<Code> insertAll(Collection<Code> entities) throws GenesisException {
		if (!entities.isEmpty()) {
			for (Code entity: entities) {
				entity.setCodeFormat(findFormat(entity));
			}
			return super.insertAll(entities);
		} else {
			return List.of();
		}
	}
	
	private CodeFormat findFormat(CodeEntity code) {
		Optional<CodeFormat> maybeFormat = formatRepository.findByType(code.getType());
		if (maybeFormat.isPresent()) {
			return maybeFormat.get();
		} else {
			AppPreference format = appPreferenceService.findByKey(DefaultAppPreference.CODE_FORMAT);
			return formatRepository.saveAndFlush(new CodeFormat(code.getType(), format.getValue()));
		}
	}
}
