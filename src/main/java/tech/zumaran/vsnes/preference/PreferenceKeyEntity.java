package tech.zumaran.vsnes.preference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tech.zumaran.genesis.GenesisEntity;

@Entity
@ToString
@NoArgsConstructor
@Table(name = "preference_key")
@EqualsAndHashCode(callSuper = false)
public class PreferenceKeyEntity extends GenesisEntity implements PreferenceKey {
	
	private static final long serialVersionUID = -1848303021824598740L;
	
	@Column(nullable = false, unique = true)
	@Getter @Setter private String name;
	
	@Column(nullable = false)
	@Getter @Setter private String defaultValue;
	
	public PreferenceKeyEntity(PreferenceKey key) {
		this.name = key.getName();
		this.defaultValue = key.getDefaultValue();
	}
	
}

