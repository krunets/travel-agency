package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.util.config.DevelopmentDatabaseBeanConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static by.runets.travelagency.util.config.DevelopmentDatabaseBeanConfig.DEFAULT_PAGINATION_SIZE;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "development")
@ContextConfiguration(classes = DevelopmentDatabaseBeanConfig.class)
@SqlGroup(
		@Sql(
				executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
				scripts = {"classpath:db/init-data.sql"}
		))
public class HotelRepositoryTest {
	@Autowired
	private IDatabaseRepository<Hotel, Long> hotelRepository;
	
	@Test
	public void testReadById () {
		final long id = 1;
		final Hotel expected = new Hotel(id, "Marriot", "123 23 23", 5, new Country(1, null, null, null));
		final Hotel actual = hotelRepository.read(Hotel.class, id).get();
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testReadAll () {
		final List<Optional<Hotel>> expected =
				new ArrayList<>(
						Arrays.asList(
								Optional.of(
										new Hotel(1, "Marriot", "123 23 23", 5, new Country())),
								Optional.of(
										new Hotel(
												2, "DoubleTree by Hilton", "232 12 12", 5, new Country())),
								Optional.of(
										new Hotel(
												3, "Prezident-Otel", "111 11 11", 4, new Country())),
								Optional.of(
										new Hotel(4, "Aqua-Minsk", "123 11 11", 2, new Country())),
								Optional.of(
										new Hotel(
												5,
												"Trump International Hotel Washington DC",
												"101 10 01",
												5,
												new Country()))));
		final List<Optional<Hotel>> actual = hotelRepository.readAll(Hotel.class, DEFAULT_PAGINATION_SIZE);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testCreate () {
		final Hotel expected = new Hotel(10, "testName", "+375 29 123 123 123", 5, new Country(1, null, null, null));
		final long id = hotelRepository.create(expected);
		final Hotel actual = hotelRepository.read(Hotel.class, id).get();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdate () {
		final long id = 1;
		final Hotel expected = hotelRepository.read(Hotel.class, id).get();
		
		expected.setName("newName");
		expected.setStars(10);
		expected.setPhone("111 111 11");
		
		hotelRepository.update(expected);
		
		final Hotel actual = hotelRepository.read(Hotel.class, id).get();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testDelete () {
		final long id = 1;
		final Optional<Hotel> expected = hotelRepository.read(Hotel.class, id);
		Assert.assertNotNull(expected);
		hotelRepository.delete(expected.get());
		
		final Optional<Hotel> actual = hotelRepository.read(Hotel.class, id);
		Assert.assertEquals(Optional.empty(), actual);
	}
}
