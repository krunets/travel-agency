package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.repository.IDatabaseRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends AbstractService<Country, Integer> {
	public CountryService (IDatabaseRepository<Country, Integer> repository) {
		super(repository);
	}
}
