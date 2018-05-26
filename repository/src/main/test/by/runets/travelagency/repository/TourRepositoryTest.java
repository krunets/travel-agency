package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.repository.impl.TourRepository;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TourRepositoryTest {
	private final IRepository<Tour, Integer> repository = new TourRepository();
	
	@Test
	public void readAllTest () {
		List<Optional<Tour>> actual = new ArrayList<>(Arrays.asList(
				Optional.of(new Tour<Integer>(1, "Photo1", LocalDateTime.now().plusDays(10), Duration.ofDays(10), "description1", new BigDecimal(100))),
				Optional.of(new Tour<Integer>(2, "Photo2", LocalDateTime.now().plusDays(20), Duration.ofDays(20), "description1", new BigDecimal(200))),
				Optional.of(new Tour<Integer>(3, "Photo3", LocalDateTime.now().plusDays(30), Duration.ofDays(30), "description1", new BigDecimal(300))),
				Optional.of(new Tour<Integer>(4, "Photo4", LocalDateTime.now().plusDays(40), Duration.ofDays(40), "description1", new BigDecimal(400))),
				Optional.of(new Tour<Integer>(5, "Photo5", LocalDateTime.now().plusDays(50), Duration.ofDays(50), "description1", new BigDecimal(500))))
		);
		
		
		List<Optional<Tour>> expected = repository.readAll();
		Assert.assertEquals(actual, expected);
	}
	
	
}
