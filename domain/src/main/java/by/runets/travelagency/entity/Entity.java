package by.runets.travelagency.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Common class which provides generic id field.
 *
 * @param <K> is a generic param which represents a key param.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity<K> {
	/**
	 * This is a field which represents a id key of entity.
	 */
	private K id;
}
