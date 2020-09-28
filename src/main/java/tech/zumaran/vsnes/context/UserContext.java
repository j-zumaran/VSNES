package tech.zumaran.vsnes.context;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.zumaran.genesis.context.GenesisContext;

@Entity
@NoArgsConstructor
public class UserContext extends GenesisContext {

	private static final long serialVersionUID = 5907063593637305621L;
	
	@OneToMany(mappedBy = "context", fetch = FetchType.EAGER)
	@Getter @Setter private Set<UserPreference> userPreferences;
	
	public UserContext(long userId) {
		super(userId);
	}

}
