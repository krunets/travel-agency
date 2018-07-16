package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.hibernate.impl.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends AbstractService<Country> {
	public CountryService (CountryRepository repository, Class<Country> classType) {
		super(repository, classType);
	}
}
