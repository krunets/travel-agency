package by.runets.travelagency.joiner;

import java.util.List;

public interface Joiner <T> {
	List<T> join(List<T> entities);
}