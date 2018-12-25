package by.runets.travelagency.cache.impl;

import by.runets.travelagency.cache.ICountryCache;
import by.runets.travelagency.entity.Country;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.runets.travelagency.util.constant.PaginationConstant.DEFAULT_COUNTRY_PAGINATION_SIZE;

@Service
@CacheConfig
public class CountryCache implements ICountryCache {
  @Autowired
  private IDatabaseRepository<Country, Long> countryRepository;

  @Override
  @Cacheable("countries")
  public List<Country> readAll() {
    return countryRepository.readAll(Country.class, DEFAULT_COUNTRY_PAGINATION_SIZE)
        .stream()
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());
  }
}
