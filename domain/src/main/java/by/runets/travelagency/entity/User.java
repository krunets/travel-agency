package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Class that represents the entity of the user.
 *
 * @param <K> is a generic param which represents a key param.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
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
	 * Constructor with arguments.
	 *
	 * @param id       constructor argument which initializes user id field.
	 * @param login    constructor argument which initializes user login field.
	 * @param password constructor argument which initializes user password field.
	 */
	public User (final K id, final String login, final String password) {
		super(id);
		this.login = login;
		this.password = password;
	}
}
