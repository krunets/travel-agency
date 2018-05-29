package by.runets.travelagency.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Common class which provides generic id field.
 *
 * @param <K> is a generic param which represents a key param.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity<K> {
	private K id;
}
