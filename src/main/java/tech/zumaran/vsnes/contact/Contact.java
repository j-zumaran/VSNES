package tech.zumaran.vsnes.contact;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tech.zumaran.vsnes.category.Status;
import tech.zumaran.vsnes.code.CodeEntity;
import tech.zumaran.vsnes.location.Location;

@Data
@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class Contact extends CodeEntity {
	
	private static final long serialVersionUID = 7839125304127110232L;

	@Column(nullable = false)
	private String email;
	
	private String phone;
	
	private String address;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Location location = Location.defaultLocation();
	
	@ManyToOne
	private Status currentStatus;

}
