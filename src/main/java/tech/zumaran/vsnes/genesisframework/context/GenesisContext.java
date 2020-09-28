package tech.zumaran.vsnes.genesisframework.context;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tech.zumaran.vsnes.genesisframework.GenesisEntity;

@ToString
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class GenesisContext extends GenesisEntity {
	
	private static final long serialVersionUID = 7389942231760491853L;
	
	@Column(nullable = false, unique = true)
	@Setter private long contextId;
	
	@JsonIgnore
	public long getContextId() {
		return contextId;
	}
	
}
