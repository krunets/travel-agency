package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.TourType;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Is inherited from common class and provides list of tours.
 */
public class TourRepository extends AbstractRepository<Tour, Integer> {
	private static List<Tour> tours = new ArrayList<>(Arrays.asList(
			new Tour<>(1, "Photo1", LocalDate.parse("2018-07-17"), Duration.ofDays(10), "description1", new BigDecimal(100), TourType.ADVENTURE, null, null),
			new Tour<>(2, "Photo2", LocalDate.parse("2018-07-20"), Duration.ofDays(20), "description2", new BigDecimal(200), TourType.ADVENTURE, null, null),
			new Tour<>(3, "Photo3", LocalDate.parse("2018-07-25"), Duration.ofDays(30), "description3", new BigDecimal(300), TourType.ADVENTURE, null, null),
			new Tour<>(4, "Photo4", LocalDate.parse("2018-07-30"), Duration.ofDays(40), "description4", new BigDecimal(400), TourType.ADVENTURE, null, null),
			new Tour<>(5, "Photo5", LocalDate.parse("2018-08-05"), Duration.ofDays(50), "description5", new BigDecimal(500), TourType.ADVENTURE, null, null))
	);
	
	public TourRepository () {
		super(tours);
	}
}