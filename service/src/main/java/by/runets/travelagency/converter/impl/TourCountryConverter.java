package by.runets.travelagency.converter.impl;

import by.runets.travelagency.converter.Converter;
import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TourCountryConverter implements Converter<List<Tour>, List<Tour>> {

  @Autowired
  private Converter<List<Country>, List<Country>> countryCodeToCountryConverter;

  @Override
  public List<Tour> convert(List<Tour> tourList) {

    final List<Tour> tours = new ArrayList<>(tourList.size());

	tourList.forEach(tour -> {
	  List<Country> convertedCountries = countryCodeToCountryConverter.convert(tour.getCountries());
	  tour.setCountries(convertedCountries);
	  tours.add(tour);
	});

    return tours;
  }
}
