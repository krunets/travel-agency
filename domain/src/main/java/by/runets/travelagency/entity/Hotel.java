package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Class that represents the entity of the hotel.
 * @param <K> is a generic param which represents a key param.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Hotel<K> extends Entity<K> {
	private String name;
	private String phone;
	private int stars;
	
	/**
	 * Constructor with arguments.
	 * @param id constructor argument which initializes hotel id field.
	 * @param name constructor argument which initializes hotel name field.
	 * @param phone constructor argument which initializes hotel phone field.
	 * @param stars constructor argument which initializes hotel stars field.
	 */
	public Hotel(final K id, final String name, final String phone, final int stars) {
		super(id);
		this.name = name;
		this.phone = phone;
		this.stars = stars;
	}
}
