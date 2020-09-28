package tech.zumaran.vsnes.contact;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class IDContact extends Contact {

	private static final long serialVersionUID = 5090861723802521547L;

	@Column(nullable = false, unique = true)
	private String idNumber;
	
	@Column(nullable = false)
	private Reputation reputation = Reputation.B;
	
}
