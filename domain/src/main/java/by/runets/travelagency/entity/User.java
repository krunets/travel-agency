package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Class that represents the entity of the user.
 *
 * @param <K> is a generic param which represents a key param.
 */

@Data

@NoArgsConstructor
@ToString(exclude = {"reviews", "tours"}, callSuper = true)
@EqualsAndHashCode(exclude = {"reviews", "tours"}, callSuper = true)
public class User<K> extends Entity<K> {
	/**
	 * This is a field which represents a user login.
	 */
	private String login;
	/**
	 * This is a field which represents a user password.
	 */
	private String password;
	/**
	 * This is a field which represents a user review of tour.
	 */
	private List<Review<K>> reviews;
	
	private List<Tour<K>> tours;
	
	/**
	 * Constructor with arguments.
	 *
	 * @param id       constructor argument which initializes user id field.
	 * @param login    constructor argument which initializes user login field.
	 * @param password constructor argument which initializes user password field.
	 * @param reviews   constructor argument which initializes user reviews field.
	 * @param tours   constructor argument which initializes user tours field.
	 */
	public User (final K id, final String login, final String password, final List<Review<K>> reviews, final List<Tour<K>>  tours) {
		super(id);
		this.login = login;
		this.password = password;
		this.reviews = reviews;
		this.tours = tours;
	}
}
