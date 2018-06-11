package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Class that represents the entity of the hotel.
 *
 * @param <K> is a generic param which represents a key param.
 */
@EqualsAndHashCode(exclude = "country", callSuper = true)
@Data
@NoArgsConstructor
@ToString(exclude = "country", callSuper = true)
public class Hotel<K> extends Entity<K> {
	/**
	 * This is a field which represents hotel name.
	 */
	private String name;
	/**
	 * This is a field which represents a hotel phone.
	 */
	private String phone;
	/**
	 * This is a field which represents a hotel stars rating.
	 */
	private int stars;
	/**
	 * This is a field which represents a country.
	 */

	private Country<K> country;
	
	/**
	 * Constructor with arguments.
	 *
	 * @param id    constructor argument which initializes hotel id field.
	 * @param name  constructor argument which initializes hotel name field.
	 * @param phone constructor argument which initializes hotel phone field.
	 * @param stars constructor argument which initializes hotel stars field.
	 */
	public Hotel (final K id, final String name, final String phone, final int stars, final Country<K> country) {
		super(id);
		this.name = name;
		this.phone = phone;
		this.stars = stars;
		this.country = country;
	}
}
