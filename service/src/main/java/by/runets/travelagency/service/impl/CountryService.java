package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.repository.ICollectionRepository;

public class CountryService extends AbstractService<Country, Integer> {
	public CountryService (ICollectionRepository<Country, Integer> repository) {
		super(repository);
	}
}
