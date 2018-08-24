package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.service.ICountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends AbstractService<Country, Long> implements ICountryService<Country, Long> {
	public CountryService (Class<Country> classType, IDatabaseRepository<Country, Long> abstractRepository) {
		super(classType, abstractRepository);
	}
}
