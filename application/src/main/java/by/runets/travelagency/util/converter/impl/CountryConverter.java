package by.runets.travelagency.util.converter.impl;

import by.runets.travelagency.dto.CountryDTO;
import by.runets.travelagency.entity.Country;
import by.runets.travelagency.util.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CountryConverter implements Converter<List<CountryDTO>, List<Country>> {
	@Override
	public List<CountryDTO> convert (List<Country> countryList) {
		List<CountryDTO> countries = new ArrayList<>();
		
		for (Country country : countryList) {
			String code = country.getName();
			Locale locale = new Locale("", code);
			String name = locale.getDisplayCountry();
			
			countries.add(new CountryDTO(code, name));
		}
		
		return countries;
	}
}
