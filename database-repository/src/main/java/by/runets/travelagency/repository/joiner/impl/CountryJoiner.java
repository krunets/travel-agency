package by.runets.travelagency.repository.joiner.impl;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.repository.joiner.Joiner;
import org.springframework.stereotype.Component;

import java.util.*;

@Deprecated
@Component
public class CountryJoiner implements Joiner<Country> {
	@Override
	public List<Country> join (List<Country> countries) {
		Set<Country> result = new LinkedHashSet<>();
		
		for (int i = 0; i < countries.size(); i++) {
			Set<Tour> tours = new HashSet<>();
			Set<Hotel> hotels = new HashSet<>();
			
			tours.add((Tour) countries.get(i).getTours().get(0));
			hotels.add((Hotel) countries.get(i).getHotels().get(0));
			for (int j = i + 1; j < countries.size(); j++) {
				if (countries.get(i).getId() == countries.get(j).getId()) {
					tours.add((Tour) countries.get(j).getTours().get(0));
					hotels.add((Hotel) countries.get(j).getHotels().get(0));
					countries.remove(j);
				}
			}
			countries.get(i).setTours(new ArrayList<>(tours));
			countries.get(i).setHotels(new ArrayList<>(hotels));
			result.add(countries.get(i));
		}
		
		return new ArrayList<>(result);
	}
}
