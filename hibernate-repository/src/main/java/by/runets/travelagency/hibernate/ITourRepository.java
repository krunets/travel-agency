package by.runets.travelagency.hibernate;

import by.runets.travelagency.entity.Tour;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITourRepository extends IDatabaseRepository<Tour, Long> {
  List<Optional<Tour>> findTourByCountryAndDateAndDuration(
      final String countryName,
      final LocalDate startTourDate,
      final List<Duration> durations,
      final int page,
      final int limit);

}
