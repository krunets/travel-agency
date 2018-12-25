package by.runets.travelagency.service;

import by.runets.travelagency.dto.CountryDTO;
import by.runets.travelagency.entity.Country;

import java.util.List;

public interface ICountryService extends IService<Country, Long> {
  List<CountryDTO> filterByCriteria(String searchCriteria);
}
