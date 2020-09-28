package tech.zumaran.vsnes.code;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tech.zumaran.genesis.GenesisEntity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CodeFormat extends GenesisEntity {
	
	private static final long serialVersionUID = -523161152472342031L;

	@Column(nullable = false, unique = true)
	private String type;

	@Column(nullable = false)
	private String regex;
}
