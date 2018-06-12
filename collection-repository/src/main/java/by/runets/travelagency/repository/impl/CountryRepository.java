package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Hotel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Is inherited from common class and provides list of countries.
 */
public class CountryRepository extends AbstractRepository<Country, Integer> {
	public CountryRepository (List<Country> countries) {
		super(countries);
	}
}
