package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Class that represents the entity of the hotel.
 *
 * @param <K> is a generic param which represents a key param.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
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
	 * Constructor with arguments.
	 *
	 * @param id    constructor argument which initializes hotel id field.
	 * @param name  constructor argument which initializes hotel name field.
	 * @param phone constructor argument which initializes hotel phone field.
	 * @param stars constructor argument which initializes hotel stars field.
	 */
	public Hotel (final K id, final String name, final String phone, final int stars) {
		super(id);
		this.name = name;
		this.phone = phone;
		this.stars = stars;
	}
}
