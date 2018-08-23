package by.runets.travelagency.hibernate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITourRepository<T, K> extends IDatabaseRepository<T, K> {
	List<Optional<T>> findTourByCountryAndDuration(final K countryId, final LocalDate startTourDate, final LocalDate endTourDate);
}