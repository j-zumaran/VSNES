package tech.zumaran.vsnes.location;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tech.zumaran.vsnes.genesisframework.GenesisEntity;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Location extends GenesisEntity {
	
	private static final long serialVersionUID = 561131590070293841L;

	@Column(nullable = false)
	private double longitude;
	
	@Column(nullable = false)
	private double latitude;
	
	private String description;

	@JsonIgnore
	public static Location defaultLocation() {
		Location l = new Location();
		l.setLatitude(0);
		l.setLongitude(0);
		return l;
	}
}
