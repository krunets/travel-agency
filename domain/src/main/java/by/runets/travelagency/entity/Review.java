package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Class that represents the entity of the review.
 * @param <K> is a generic param which represents a key param.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Review<K> extends Entity<K> {
	private String content;
	
	/**
	 * Constructor with arguments.
	 * @param id constructor argument which initializes review id field.
	 * @param content constructor argument which initializes review content field.
	 */
	public Review(final K id, final String content) {
		super(id);
		this.content = content;
	}
}