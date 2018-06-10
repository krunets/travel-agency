package by.runets.travelagency.entity;

import lombok.*;

import java.util.List;


/**
 * Class that represents the entity of the country.
 *
 * @param <K> is a generic param which represents a key param.
 */
@EqualsAndHashCode(exclude = {"hotels", "tours"}, callSuper = true)
@Data
@NoArgsConstructor
@ToString(exclude = {"hotels", "tours"}, callSuper = true)
public class Country<K> extends Entity<K> {
	/**
	 * This is a field which represents a Country name.
	 */
	private String name;
	/**
	 * This is a field which represents a list of hotels.
	 */
	private List<Hotel<K>> hotels;
	/**
	 * This is a field which represents a list of tours in exist country.
	 */
	private List<Tour<K>> tours;
	
	/**
	 * Constructor with arguments.
	 *
	 * @param id   constructor argument which initializes country id field.
	 * @param name constructor argument which initializes country name field.
	 * @param hotels constructor argument which initializes country hotels field.
	 * @param tours constructor argument which initializes country tours field.
	 */
	public Country (final K id, final String name, final List<Hotel<K>> hotels, final List<Tour<K>> tours) {
		super(id);
		this.name = name;
		this.hotels = hotels;
		this.tours = tours;
	}
}
