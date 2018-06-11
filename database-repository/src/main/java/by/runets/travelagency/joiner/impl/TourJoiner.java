package by.runets.travelagency.joiner.impl;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.joiner.Joiner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TourJoiner implements Joiner<Tour> {
	@Override
	public List<Tour> join (List<Tour> tours) {
		List<Tour> result = new ArrayList<>();
		for (int i = 0; i < tours.size(); i++) {
			if (i + 1 < tours.size() && tours.get(i).getId() == tours.get(i + 1).getId()) {
				Set<Country> countries = new HashSet<>();
				Set<User> users = new HashSet<>();
				
				countries.add((Country) tours.get(i).getCountries().get(0));
				countries.add((Country) tours.get(i + 1).getCountries().get(0));
				
				users.add((User) tours.get(i).getUsers().get(0));
				users.add((User) tours.get(i + 1).getUsers().get(0));
				
				tours.get(i).setCountries(new ArrayList<>(countries));
				tours.get(i).setUsers(new ArrayList<>(users));
				
				tours.remove(i + 1);
			}
			result.add(tours.get(i));
		}
		return result;
	}
}
