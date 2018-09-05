package by.runets.travelagency.service;

import java.util.List;

public interface IJoinService<T1, T2> {
	void join (List<T1> first, List<T2> second);
}
