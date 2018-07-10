package integration.by.runets.travelagency.service;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.service.impl.HotelService;
import integration.by.runets.travelagency.config.IntegrationServiceTestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "development")
@ContextConfiguration(classes = IntegrationServiceTestConfig.class)
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/schema.sql", "classpath:db/init-data.sql"}),
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/drop.sql")
})
public class HotelServiceTest {
	@Autowired
	private HotelService service;

	@Test
	public void testCreate() {
		Hotel<Integer> expected =
				new Hotel<>(10, "testName", "+375 29 123 123 123", 5, new Country<>(1, null, null, null));
		service.create(expected);
		Hotel<Integer> actual = service.read(10);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadById() {
		Hotel<Integer> expected =
				new Hotel<>(1, "Marriot", "123 23 23", 5, new Country<>(1, null, null, null));
		Hotel<Integer> actual = service.read(1);
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void	testReadAll() {
		List<Hotel> expected =
				new ArrayList<>(
						Arrays.asList(
								
										new Hotel<Integer>(1, "Marriot", "123 23 23", 5, new Country<Integer>()),
								
										new Hotel<Integer>(
												2, "DoubleTree by Hilton", "232 12 12", 5, new Country<Integer>()),
								
										new Hotel<Integer>(
												3, "Prezident-Otel", "111 11 11", 4, new Country<Integer>()),
								
										new Hotel<Integer>(4, "Aqua-Minsk", "123 11 11", 2, new Country<Integer>()),
		
										new Hotel<Integer>(
												5,
												"Trump International Hotel Washington DC",
												"101 10 01",
												5,
												new Country<Integer>())));
		List<Hotel> actual = service.readAll();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testDelete() {
		Hotel expected = service.read(1);
		Assert.assertNotNull(expected);
		service.delete(expected);
		
		Hotel actual = service.read((Integer) expected.getId());
		Assert.assertNull(actual);
	}
	
	@Test
	public void testUpdate() {
		Hotel<Integer> expected = service.read(1);
		
		expected.setName("newName");
		expected.setStars(10);
		expected.setPhone("111 111 11");
		
		service.update(expected);
		
		Hotel<Integer> actual = service.read(1);
		
		Assert.assertEquals(expected, actual);
	}
}
