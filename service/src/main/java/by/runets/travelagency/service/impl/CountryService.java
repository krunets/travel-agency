package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.service.ICountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends AbstractService<Country> implements ICountryService {
  public CountryService(Class<Country> classType, IDatabaseRepository<Country, Long> abstractRepository) {
	super(classType, abstractRepository);
  }
}
