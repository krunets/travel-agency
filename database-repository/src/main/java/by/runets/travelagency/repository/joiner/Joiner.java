package by.runets.travelagency.repository.joiner;

import java.util.List;

/**
 * @param <T> is
 */

public interface Joiner <T> {
	List<T> join(List<T> entities);
}