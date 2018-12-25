package by.runets.travelagency.converter.impl;

import by.runets.travelagency.converter.Converter;
import by.runets.travelagency.entity.Country;
import by.runets.travelagency.util.constant.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CountryCodeToCountryConverter implements Converter<List<Country>, List<Country>> {
  @Override
  public List<Country> convert(List<Country> countryList) {
    final List<Country> countries = new ArrayList<>(countryList.size());
    countryList.forEach(
        country -> {
          String code = country.getName();
          Locale locale = new Locale(StringUtils.EN_LOCALE, code);
          country.setName(locale.getDisplayCountry(locale));
		  countries.add(country);
        });

    return countries;
  }
}
