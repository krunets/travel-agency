package integration.by.runets.travelagency.service;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.service.IService;
import integration.by.runets.travelagency.config.DevelopmentDatabaseBeanConfig;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Transactional
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "development")
@ContextConfiguration(classes = DevelopmentDatabaseBeanConfig.class)
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/init-data.sql"})
})
public class HotelServiceTest {
	@Autowired
	private IService<Hotel, Long> hotelService;
	
	@Test
	public void testCreate () {
		Hotel expected =
				new Hotel(10, "testName", "+375 29 123 123 123", 5, new Country(1, null, null, null));
		final long id = hotelService.create(expected);
		Hotel actual = hotelService.read(id);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadById () {
		final long id = 1;
		Hotel expected =
				new Hotel(1, "Marriot", "123 23 23", 5, new Country(1, null, null, null));
		Hotel actual = hotelService.read(id);
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testReadAll () {
		List<Hotel> expected =
				new ArrayList<>(
						Arrays.asList(
								
								new Hotel(1, "Marriot", "123 23 23", 5, new Country()),
								
								new Hotel(
										2, "DoubleTree by Hilton", "232 12 12", 5, new Country()),
								
								new Hotel(
										3, "Prezident-Otel", "111 11 11", 4, new Country()),
								
								new Hotel(4, "Aqua-Minsk", "123 11 11", 2, new Country()),
								
								new Hotel(
										5,
										"Trump International Hotel Washington DC",
										"101 10 01",
										5,
										new Country())));
		List<Hotel> actual = hotelService.readAll();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testDelete () {
		final long id = 1;
		Hotel expected = hotelService.read(id);
		Assert.assertNotNull(expected);
		hotelService.delete(expected);
		
		Hotel actual = hotelService.read(id);
		Assert.assertNull(actual);
	}
	
	@Test
	public void testUpdate () {
		final long id = 1;
		Hotel expected = hotelService.read(id);
		
		expected.setName("newName");
		expected.setStars(10);
		expected.setPhone("111 111 11");
		
		hotelService.update(expected);
		
		Hotel actual = hotelService.read(id);
		
		Assert.assertEquals(expected, actual);
	}
}
