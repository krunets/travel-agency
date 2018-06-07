package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Class that represents the entity of the review.
 *
 * @param <K> is a generic param which represents a key param.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Review<K> extends Entity<K> {
	/**
	 * This is a field which represents a content of review.
	 */
	private String content;
	/**
	 * This is a field which represents a user who left feedback about tour.
	 */
	private User user;
	
	/**
	 * Constructor with arguments.
	 *
	 * @param id      constructor argument which initializes review id field.
	 * @param content constructor argument which initializes review content field.
	 * @param user constructor argument which initializes review user field.
	 */
	public Review (final K id, final String content, final User user) {
		super(id);
		this.content = content;
		this.user = user;
	}
}