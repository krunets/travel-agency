package by.runets.travelagency.hibernate;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITourRepository<T, K> extends IDatabaseRepository<T, K> {
	List<Optional<T>> findTourByCountryAndDateAndDuration (final String countryName, final LocalDate startTourDate, final Duration endTourDate);
}