package tech.zumaran.vsnes.preference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tech.zumaran.vsnes.genesisframework.GenesisEntity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AppPreference extends GenesisEntity {

	private static final long serialVersionUID = 4460814522797561407L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private PreferenceKeyEntity key;
	
	@Column(nullable = false)
	private String value;
}
