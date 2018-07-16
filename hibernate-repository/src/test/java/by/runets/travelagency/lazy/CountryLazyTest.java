package by.runets.travelagency.lazy;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.util.config.DevelopmentDatabaseBeanConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "development")
@ContextConfiguration(classes = DevelopmentDatabaseBeanConfig.class)
@SqlGroup({
		@Sql(
				executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
				scripts = {"classpath:db/init-data.sql"}
		)
})
public class CountryLazyTest {
	@Autowired
	private IDatabaseRepository<Country, Long> countryRepository;
	
	@Test
	public void lazyCountry () {
		final long id = 1;
		Country country = countryRepository.read(Country.class, id).get();
		System.out.println("() <- got country");
		country.getHotels();
		System.out.println("() <- got hotels");
		country.getHotels().size();
		System.out.println("() <- got hotels amount");
	}
	
	@Test
	public void testNplusOne() {
		List<Optional<Country>> countries = countryRepository.readAll(Country.class);
		countries.forEach(c -> c.get().getHotels().forEach(System.out::println));
	}
	
	@Test
	public void testReadAll() {
		countryRepository.readAll(Country.class);
		countryRepository.readAll(Country.class);
	}
}
