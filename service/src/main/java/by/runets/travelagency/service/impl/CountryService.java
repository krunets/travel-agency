package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.repository.impl.AbstractRepository;

public class CountryService extends AbstractService<Country<Integer>, Integer> {
	public CountryService (AbstractRepository<Country<Integer>, Integer> repository) {
		super(repository);
	}
}
