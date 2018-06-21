package by.runets.travelagency.joiner;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @param <T> is
 */
public interface Joiner <T> {
	List<T> join(List<T> entities);
}