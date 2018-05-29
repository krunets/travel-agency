package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;


/**
 * Class that represents the entity of the country.
 *
 * @param <K> is a generic param which represents a key param.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Country<K> extends Entity<K> {
	@NonNull
	private String name;
	
	/**
	 * Constructor with arguments.
	 *
	 * @param id   constructor argument which initializes country id field.
	 * @param name constructor argument which initializes country name field.
	 */
	public Country (final K id, final String name) {
		super(id);
		this.name = name;
	}
}
