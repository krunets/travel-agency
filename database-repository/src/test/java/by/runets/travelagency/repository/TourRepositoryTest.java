package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.TourType;
import by.runets.travelagency.joiner.impl.TourJoiner;
import by.runets.travelagency.repository.impl.TourRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

public class TourRepositoryTest {
	private IRepository<Tour, Integer> repository;
	
	@Before
	public void setUp () {
		EmbeddedDatabase db =
				new EmbeddedDatabaseBuilder()
						.generateUniqueName(true)
						.setType(H2)
						.setScriptEncoding("UTF-8")
						.ignoreFailedDrops(true)
						.addScript("db/schema.sql")
						.addScript("db/init-data.sql")
						.build();
		repository = new TourRepository(new JdbcTemplate(db), new TourJoiner());
	}
	
	@Test
	public void testCreate () {
		Tour expected = new Tour<>(12, "Photo1", LocalDate.parse("2018-07-17"), Duration.ofDays(10),
				"description1", new BigDecimal(100), TourType.ADVENTURE, null, null);
		repository.create(expected);
		Optional<Tour> actual = repository.read(12);
		
		Assert.assertEquals(expected, actual.get());
	}
	
	
	@Test
	public void testReadById () {
		Optional<Tour> expected = Optional.of(new Tour<Integer>(1, "photo/img1.png", LocalDate.parse("2018-07-17"),
				Duration.ofDays(10), "description1", new BigDecimal(100), TourType.ADVENTURE, null, null));
		Optional<Tour> actual = repository.read(1);
		
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testReadAll() {
		List<Optional<Tour>> expected = new ArrayList<>(Arrays.asList(
				Optional.of(new Tour<>(1, "photo/img1.png", LocalDate.parse("2018-07-17"), Duration.ofDays(10), "description1", new BigDecimal(100), TourType.ADVENTURE, null, null)),
				Optional.of(new Tour<>(2, "photo/img2.png", LocalDate.parse("2018-07-20"), Duration.ofDays(20), "description2", new BigDecimal(200), TourType.ATOMIC , null, null)),
				Optional.of(new Tour<>(3, "photo/img3.png", LocalDate.parse("2018-07-25"), Duration.ofDays(30), "description3", new BigDecimal(300), TourType.BICYCLE, null, null)),
				Optional.of(new Tour<>(4, "photo/img4.png", LocalDate.parse("2018-07-30"), Duration.ofDays(40), "description4", new BigDecimal(400), TourType.CULTURAL, null, null)),
				Optional.of(new Tour<>(5, "photo/img5.png", LocalDate.parse("2018-08-05"), Duration.ofDays(50), "description5", new BigDecimal(500), TourType.ECO, null, null)))
		);
		
		List<Optional<Tour>> actual = repository.readAll();
		Assert.assertEquals(expected.size(), actual.size());
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testDelete() {
		Optional<Tour> entityToDelete = repository.read(1);
		Assert.assertNotNull(entityToDelete);
		
		repository.delete(entityToDelete.get());
		
		Optional<Tour> actual = repository.read(1);
		Assert.assertEquals(Optional.empty(), actual);
	}
	
	@Test
	public void testUpdate() {
		Tour<Integer> expected = repository.read(1).get();
		
		expected.setPhoto("newPhoto");
		expected.setDate(LocalDate.now());
		
		repository.update(expected);
		
		Tour<Integer> actual = repository.read(1).get();
		
		Assert.assertEquals(expected, actual);
	}
}
