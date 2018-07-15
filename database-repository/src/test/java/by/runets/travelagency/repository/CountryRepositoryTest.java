package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.repository.impl.CountryRepository;
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
@ContextConfiguration(classes = DevelopmentDatabaseBeanConfig.class)
@ActiveProfiles(profiles = "development")
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/schema.sql", "classpath:db/init-data.sql"}),
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/drop.sql")
})
public class CountryRepositoryTest {
	@Autowired
	private CountryRepository countryRepository;
	
	@Test
	public void testCreate () {
		Country expected = new Country(10, "testCountryName", null, null);
		countryRepository.create(expected);
		Country actual = countryRepository.read(10).get();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadById () {
		Country expected = new Country(1, "Belarus", null, null);
		Country actual = countryRepository.read(1).get();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadAll () {
		List<Optional<Country>> actual = countryRepository.readAll();
		
		List<Optional<Country>> expected =
				new ArrayList(
						Arrays.asList(
								Optional.of(new Country(1, "Belarus", null, null)),
								Optional.of(new Country(2, "Usa", null, null)),
								Optional.of(new Country(3, "France", null, null)),
								Optional.of(new Country(4, "Italy", null, null))));
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void deleteById () {
		Country expected = new Country(1, "", null, null);
		countryRepository.delete(expected);
		
		Assert.assertEquals(Optional.empty(), countryRepository.read(1));
	}
	
	@Test
	public void updateTest () {
		Country expected = new Country(1, "newName", null, null);
		countryRepository.update(expected);
		Country actual = countryRepository.read(1).get();
		
		Assert.assertEquals(expected, actual);
	}
}
