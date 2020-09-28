package tech.zumaran.vsnes.category;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Status extends Category {

	private static final long serialVersionUID = 358217670691142956L;

}
