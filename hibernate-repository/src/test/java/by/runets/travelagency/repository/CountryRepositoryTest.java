package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.util.config.DevelopmentDatabaseBeanConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "development")
@ContextConfiguration(classes = DevelopmentDatabaseBeanConfig.class)
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/schema.sql", "classpath:db/init-data.sql"}),
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/drop.sql")
})
public class CountryRepositoryTest {
	@Autowired
	private IDatabaseRepository<Country, Long> countryRepository;
	
	@Test
	public void testReadAll () {
		List<Optional<Country>> expected =
				new ArrayList(
						Arrays.asList(
								Optional.of(new Country(1, "Belarus", null, null)),
								Optional.of(new Country(2, "Usa", null, null)),
								Optional.of(new Country(3, "France", null, null)),
								Optional.of(new Country(4, "Italy", null, null))));
		List<Optional<Country>> actual = countryRepository.readAll(Country.class);
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testCreate() {
		final long id = 10;
		final Country expected = new Country(10, "testCountryName", null, null);
		countryRepository.create(expected);
		final Country actual = countryRepository.read(Country.class, id).get();
		
		Assert.assertEquals(expected, actual);
	}
}
