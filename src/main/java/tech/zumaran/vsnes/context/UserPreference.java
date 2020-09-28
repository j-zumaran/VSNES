package tech.zumaran.vsnes.context;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tech.zumaran.vsnes.preference.PreferenceKeyEntity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_preferences")
@EqualsAndHashCode(callSuper = false)
public class UserPreference extends UserEntity<UserContext> {

	private static final long serialVersionUID = 4460814522797561407L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private PreferenceKeyEntity key;
	
	@Column(nullable = false)
	private String value;

}
