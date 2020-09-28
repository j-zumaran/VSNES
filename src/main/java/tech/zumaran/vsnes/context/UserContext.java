package tech.zumaran.vsnes.context;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tech.zumaran.vsnes.genesisframework.GenesisEntity;

@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class UserContext extends GenesisEntity {
	
	private static final long serialVersionUID = 7389942231760491853L;
	
	@Column(nullable = false, unique = true)
	@Setter private long userId;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "context")
	@Getter @Setter private Set<UserPreference> userPreferences;
	
	public UserContext(long userId) {
		this.userId = userId;
	}
	
	@JsonIgnore
	public long getUserId() {
		return userId;
	}
	
}
