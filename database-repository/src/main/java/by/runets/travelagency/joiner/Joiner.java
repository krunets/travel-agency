package by.runets.travelagency.joiner;

import java.util.List;

/**
 *
 * @param <T> is
 */
@Deprecated
public interface Joiner <T> {
	List<T> join(List<T> entities);
}