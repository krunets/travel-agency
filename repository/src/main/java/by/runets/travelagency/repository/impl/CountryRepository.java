package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Is inherited from common class and provides list of countries.
 */
public class CountryRepository extends AbstractRepository<Country, Integer> {
	private static List<Country> countries = new ArrayList<>(Arrays.asList(
			new Country<Integer>(1, "Belarus"),
			new Country<Integer>(2, "USA"),
			new Country<Integer>(3, "France"),
			new Country<Integer>(4, "Italy")));
	
	public CountryRepository () {
		super(countries);
	}
}
