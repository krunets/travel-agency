package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Hotel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Is inherited from common class and provides list of countries.
 */
public class CountryRepository extends AbstractRepository<Country, Integer> {
	private static List<Country> countries = new ArrayList<>(Arrays.asList(
			new Country<>(1, "Belarus", null, null),
			new Country<>(2, "USA", null, null),
			new Country<>(3, "France", null, null),
			new Country<>(4, "Italy", null, null)
	));
	public CountryRepository () {
		super(countries);
	}
}
