package by.runets.travelagency.repository.joiner.impl;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.repository.joiner.Joiner;
import org.springframework.stereotype.Component;

import java.util.*;
@Deprecated
@Component
public class TourJoiner implements Joiner<Tour> {
	@Override
	public List<Tour> join (List<Tour> tours) {
		Set<Tour> result = new LinkedHashSet<>();
		for (int i = 0; i < tours.size(); i++) {
			Set<Country> countries = new HashSet<>();
			Set<User> users = new HashSet<>();
			
			countries.add((Country) tours.get(i).getCountries().get(0));
			users.add((User) tours.get(i).getUsers().get(0));
			for (int j = i + 1; j < tours.size(); j++) {
				if (tours.get(i).getId() == tours.get(j).getId()) {
					countries.add((Country) tours.get(j).getCountries().get(0));
					users.add((User) tours.get(j).getUsers().get(0));
					tours.remove(j);
				}
			}
			tours.get(i).setCountries(new ArrayList<>(countries));
			tours.get(i).setUsers(new ArrayList<>(users));
			result.add(tours.get(i));
		}
		return new ArrayList<>(result);
	}
}
