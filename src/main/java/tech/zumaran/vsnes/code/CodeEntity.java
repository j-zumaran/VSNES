package tech.zumaran.vsnes.code;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Setter;
import lombok.ToString;
import tech.zumaran.genesis.GenesisEntity;

@ToString
@MappedSuperclass
public abstract class CodeEntity extends GenesisEntity {

	private static final long serialVersionUID = -7837254358350358492L;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@Setter private CodeFormat codeFormat;
	
	@JsonIgnore
	public CodeFormat getCodeFormat() {
		return codeFormat;
	}
	
	public String getCode() throws CodeFormatError {
		return applyFormat(codeFormat);
	}
	
	protected String applyFormat(CodeFormat codeFormat) throws CodeFormatError {
		String regex = codeFormat.getRegex();
		return String.format("{}{}{}", getId(), getClass().getSimpleName(), regex);
	}
	
}
