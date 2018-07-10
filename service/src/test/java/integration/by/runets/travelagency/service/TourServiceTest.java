package integration.by.runets.travelagency.service;

import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.TourType;
import by.runets.travelagency.service.impl.TourService;
import integration.by.runets.travelagency.config.IntegrationServiceTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = IntegrationServiceTestConfig.class)
@ActiveProfiles(profiles = "development")
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/schema.sql", "classpath:db/init-data.sql"}),
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/drop.sql")
})
public class TourServiceTest {
	@Autowired
	private TourService service;

	@Test
	public void testCreate() {
		Tour expected =
				new Tour<>(
						12,
						"Photo1",
						LocalDate.parse("2018-07-17"),
						Duration.ofDays(10),
						"description1",
						new BigDecimal(100),
						TourType.ADVENTURE,
						null,
						null);
		service.create(expected);
		Tour actual = service.read(12);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadAll() {
		List<Tour> expected =
				new ArrayList<>(
						Arrays.asList(
										new Tour<>(1, "photo/img1.png", LocalDate.parse("2018-07-17"), Duration.ofDays(10), "description1", new BigDecimal(100), TourType.ADVENTURE, null, null),
								new Tour<>(2, "photo/img2.png", LocalDate.parse("2018-07-20"), Duration.ofDays(20), "description2", new BigDecimal(200), TourType.ATOMIC, null, null),
								new Tour<>(3, "photo/img3.png", LocalDate.parse("2018-07-25"), Duration.ofDays(30), "description3", new BigDecimal(300), TourType.BICYCLE, null, null),
								new Tour<>(4, "photo/img4.png", LocalDate.parse("2018-07-30"), Duration.ofDays(40), "description4", new BigDecimal(400), TourType.CULTURAL, null, null),
								new Tour<>(5, "photo/img5.png", LocalDate.parse("2018-08-05"), Duration.ofDays(50), "description5", new BigDecimal(500), TourType.ECO, null, null)));
		
		List<Tour> actual = service.readAll();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadById() {
		Tour expected =
				new Tour<>(
						1,
						"photo/img1.png",
						LocalDate.parse("2018-07-17"),
						Duration.ofDays(10),
						"description1",
						new BigDecimal(100),
						TourType.ADVENTURE,
						null,
						null);
		Tour actual = service.read(1);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testDelete() {
		Tour entityToDelete = service.read(1);
		Assert.assertNotNull(entityToDelete);
		
		service.delete(entityToDelete);
		
		Tour actual = service.read(1);
		Assert.assertNull(actual);
	}
	
	@Test
	public void testUpdate() {
		Tour<Integer> expected = service.read(1);
		
		expected.setPhoto("newPhoto");
		expected.setDate(LocalDate.now());
		
		service.update(expected);
		
		Tour<Integer> actual = service.read(1);
		
		Assert.assertEquals(expected, actual);
	}
}
