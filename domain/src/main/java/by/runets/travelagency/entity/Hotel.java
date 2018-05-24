package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Hotel<K> extends Entity<K> {
	private String name;
	private String phone;
	private int stars;
	
	public Hotel (K id, String name, String phone, int stars) {
		super(id);
		this.name = name;
		this.phone = phone;
		this.stars = stars;
	}
}
