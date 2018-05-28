package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.repository.impl.TourRepository;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TourRepositoryTest {
	private final IRepository<Tour, Integer> repository = new TourRepository();
	
	@Test
	public void readAllTest () {
		List<Optional<Tour>> actual = new ArrayList<>(Arrays.asList(
				Optional.of(new Tour<Integer>(1, "Photo1", LocalDate.parse("2018-07-17"), Duration.ofDays(10), "description1", new BigDecimal(100))),
				Optional.of(new Tour<Integer>(2, "Photo2", LocalDate.parse("2018-07-20"), Duration.ofDays(20), "description1", new BigDecimal(200))),
				Optional.of(new Tour<Integer>(3, "Photo3", LocalDate.parse("2018-07-25"), Duration.ofDays(30), "description1", new BigDecimal(300))),
				Optional.of(new Tour<Integer>(4, "Photo4", LocalDate.parse("2018-07-30"), Duration.ofDays(40), "description1", new BigDecimal(400))),
				Optional.of(new Tour<Integer>(5, "Photo5", LocalDate.parse("2018-08-05"), Duration.ofDays(50), "description1", new BigDecimal(500))))
		);
		
		List<Optional<Tour>> expected = repository.readAll();
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void readByIdTest() {
		Optional<Tour> expected = repository.read(1);
		Optional<Tour> actual = Optional.of(new Tour<Integer>(1, "Photo1", LocalDate.parse("2018-07-17"), Duration.ofDays(10), "description1", new BigDecimal(100)));
		
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testDelete() {
		List<Optional<Tour>> actual = new ArrayList<>(Arrays.asList(
				Optional.of(new Tour<Integer>(2, "Photo2", LocalDate.parse("2018-07-20"), Duration.ofDays(20), "description1", new BigDecimal(200))),
				Optional.of(new Tour<Integer>(3, "Photo3", LocalDate.parse("2018-07-25"), Duration.ofDays(30), "description1", new BigDecimal(300))),
				Optional.of(new Tour<Integer>(4, "Photo4", LocalDate.parse("2018-07-30"), Duration.ofDays(40), "description1", new BigDecimal(400))),
				Optional.of(new Tour<Integer>(5, "Photo5", LocalDate.parse("2018-08-05"), Duration.ofDays(50), "description1", new BigDecimal(500))))
		);
		
		Tour entityToDelete = new Tour<Integer>(1, "Photo1", LocalDate.parse("2018-07-17"), Duration.ofDays(10), "description1", new BigDecimal(100));
		repository.delete(entityToDelete);
		
		List<Optional<Tour>> expected = repository.readAll();
		
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void testUpdate() {
		List<Optional<Tour>> actual = new ArrayList<>(Arrays.asList(
				Optional.of(new Tour<Integer>(2, "Photo23", LocalDate.parse("2018-08-20"), Duration.ofDays(20), "description12", new BigDecimal(200))),
				Optional.of(new Tour<Integer>(3, "Photo3", LocalDate.parse("2018-07-25"), Duration.ofDays(30), "description1", new BigDecimal(300))),
				Optional.of(new Tour<Integer>(4, "Photo4", LocalDate.parse("2018-07-30"), Duration.ofDays(40), "description1", new BigDecimal(400))),
				Optional.of(new Tour<Integer>(5, "Photo5", LocalDate.parse("2018-08-05"), Duration.ofDays(50), "description1", new BigDecimal(500))))
		);
		
		Tour<Integer> entityToUpdate = new Tour<Integer>(2, "Photo23", LocalDate.parse("2018-08-20"), Duration.ofDays(20), "description12", new BigDecimal(200));
		repository.update(entityToUpdate);
		
		List<Optional<Tour>> expected = repository.readAll();
		Assert.assertEquals(actual, expected);
	}
}
