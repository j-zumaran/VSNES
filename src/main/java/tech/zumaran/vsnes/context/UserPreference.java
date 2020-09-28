package tech.zumaran.vsnes.context;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.zumaran.genesis.context.ContextEntity;
import tech.zumaran.vsnes.preference.PreferenceKeyEntity;

@Data
@Entity
@Table(name = "user_preferences")
@EqualsAndHashCode(callSuper = false)
public class UserPreference extends ContextEntity<UserContext> {

	private static final long serialVersionUID = 4460814522797561407L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private PreferenceKeyEntity key;
	
	@Column(nullable = false)
	private String value;

}
