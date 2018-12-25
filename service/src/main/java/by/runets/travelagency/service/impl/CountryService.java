package by.runets.travelagency.service.impl;

import by.runets.travelagency.cache.ICountryCache;
import by.runets.travelagency.converter.Converter;
import by.runets.travelagency.dto.CountryDTO;
import by.runets.travelagency.entity.Country;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService extends AbstractService<Country> implements ICountryService {
  @Autowired private Converter<List<CountryDTO>, List<Country>> countryToCountryDTOConverter;
  @Autowired private ICountryCache countryCache;

  public CountryService(
      Class<Country> classType, IDatabaseRepository<Country, Long> abstractRepository) {
    super(classType, abstractRepository);
  }

  @Override
  @Transactional(readOnly = true)
  public List<CountryDTO> filterByCriteria(String searchCriteria) {
    final List<Country> countries = countryCache.readAll();
    return countryToCountryDTOConverter
        .convert(countries)
        .stream()
        .filter(
            country ->
                country.getValue().toLowerCase().contains(searchCriteria.toLowerCase())
                    || country.getData().toLowerCase().contains(searchCriteria.toLowerCase()))
        .collect(Collectors.toList());
  }
}
