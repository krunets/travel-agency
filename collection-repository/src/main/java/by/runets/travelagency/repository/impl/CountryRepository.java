package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Is inherited from common class and provides list of countries.
 * @deprecated this code will remove in the next version of project, also you can use database-repository
 */
@Deprecated
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
