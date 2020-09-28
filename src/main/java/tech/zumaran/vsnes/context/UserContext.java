package tech.zumaran.vsnes.context;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.zumaran.genesis.context.GenesisContext;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class UserContext extends GenesisContext {

	private static final long serialVersionUID = 5907063593637305621L;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "context")
	@Getter @Setter private Set<UserPreference> userPreferences;
	
	public UserContext(long userId) {
		super(userId);
	}

}
