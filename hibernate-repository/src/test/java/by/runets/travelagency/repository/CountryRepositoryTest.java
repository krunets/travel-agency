package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Country;
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
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/init-data.sql"}),
})
public class CountryRepositoryTest {
	@Autowired
	private IDatabaseRepository<Country, Long> countryRepository;
	
	@Test
	public void testReadAll () {
		List<Optional<Country>> expected =
				new ArrayList<>(
						Arrays.asList(
								Optional.of(new Country(1, "BY", null)),
								Optional.of(new Country(2, "US", null)),
								Optional.of(new Country(3, "FR", null)),
								Optional.of(new Country(4, "IT", null))));
		List<Optional<Country>> actual = countryRepository.readAll(Country.class, DEFAULT_PAGINATION_SIZE);
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testReadById () {
		final long id = 1;
		final Country expected = new Country(id, "BY", null);
		final Country actual = countryRepository.read(Country.class, id).get();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testCreate () {
		Country first = new Country();
		first.setName("firstName");
		
		final long id = countryRepository.create(first);
		final Country actual = countryRepository.read(Country.class, id).get();
		Assert.assertEquals(first, actual);
	}
	
	@Test
	public void testUpdate () {
		final long id = 1;
		final Country expected = new Country(id, "newName", null);
		countryRepository.update(expected);
		final Country actual = countryRepository.read(Country.class, id).get();
		
		Assert.assertEquals(expected, actual);
		
		System.out.println(actual);
	}
	
	@Test
	public void testDelete () {
		final long id = 1;
		final Country expected = new Country(id, "", null);
		countryRepository.delete(expected);
		
		Assert.assertEquals(Optional.empty(), countryRepository.read(Country.class, id));
	}
}
