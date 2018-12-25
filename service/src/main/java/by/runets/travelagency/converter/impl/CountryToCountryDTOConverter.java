package by.runets.travelagency.converter.impl;

import by.runets.travelagency.converter.Converter;
import by.runets.travelagency.dto.CountryDTO;
import by.runets.travelagency.entity.Country;
import by.runets.travelagency.util.constant.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CountryToCountryDTOConverter implements Converter<List<CountryDTO>, List<Country>> {
  @Override
  public List<CountryDTO> convert(List<Country> countryList) {
	List<CountryDTO> countries = new ArrayList<>();

	for (Country country : countryList) {
	  long id = country.getId();
	  String code = country.getName();
	  Locale locale = new Locale(StringUtils.EN_LOCALE, code);
	  String name = locale.getDisplayCountry(locale);

	  countries.add(new CountryDTO(id, name, code));
	}

	return countries;
  }
}
