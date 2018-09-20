package by.runets.travelagency.service;

import by.runets.travelagency.entity.Tour;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public interface ITourService extends IService<Tour, Long> {
  List<Tour> findTourByCountryAndDateAndDuration(final String countryName, final LocalDate startTourDate, final Duration tourDuration);
}