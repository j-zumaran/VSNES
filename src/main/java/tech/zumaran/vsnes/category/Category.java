package tech.zumaran.vsnes.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.Setter;
import tech.zumaran.vsnes.code.CodeEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Category extends CodeEntity {
	
	private static final long serialVersionUID = 7627056744935121362L;
	
	@Column(nullable = false, unique = true)
	@Getter @Setter private String name;

}
