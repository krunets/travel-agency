package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Tour;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TourRepository extends AbstractRepository<Tour, Integer> {
	private static List<Tour> tours = new ArrayList<>(Arrays.asList(
			new Tour<Integer>(1, "Photo1", LocalDateTime.now().plusDays(10), Duration.ofDays(10), "description1", new BigDecimal(100)),
			new Tour<Integer>(2, "Photo2", LocalDateTime.now().plusDays(20), Duration.ofDays(20), "description1", new BigDecimal(200)),
			new Tour<Integer>(3, "Photo3", LocalDateTime.now().plusDays(30), Duration.ofDays(30), "description1", new BigDecimal(300)),
			new Tour<Integer>(4, "Photo4", LocalDateTime.now().plusDays(40), Duration.ofDays(40), "description1", new BigDecimal(400)),
			new Tour<Integer>(5, "Photo5", LocalDateTime.now().plusDays(50), Duration.ofDays(50), "description1", new BigDecimal(500)))
	);
	
	public TourRepository () {
		super(tours);
	}
}
