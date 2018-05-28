package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.repository.IRepository;

public class CountryService extends AbstractService<Country, Integer> {
	public CountryService (IRepository<Country, Integer> repository) {
		super(repository);
	}
}
