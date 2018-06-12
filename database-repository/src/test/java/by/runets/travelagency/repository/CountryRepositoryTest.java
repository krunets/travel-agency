package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.joiner.impl.CountryJoiner;
import by.runets.travelagency.repository.impl.CountryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

public class CountryRepositoryTest {
	private IRepository<Country, Integer> repository;
	
	@Before
	public void setup () {
		EmbeddedDatabase db =
				new EmbeddedDatabaseBuilder()
						.generateUniqueName(true)
						.setType(H2)
						.setScriptEncoding("UTF-8")
						.ignoreFailedDrops(true)
						.addScript("db/schema.sql")
						.addScript("db/init-data.sql")
						.build();
		repository = new CountryRepository(new JdbcTemplate(db), new CountryJoiner());
	}
	
	@Test
	public void testCreate () {
		Country<Integer> expected = new Country<>(10, "testCountryName", null, null);
		repository.create(expected);
		Country actual = repository.read(10).get();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadById () {
		Country<Integer> expected = new Country<Integer>(1, "Belarus", null, null);
		Country actual = repository.read(1).get();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadAll () {
		List<Optional<Country>> actual = repository.readAll();
		
		List<Optional<Country>> expected = new ArrayList<>(Arrays.asList(
				Optional.of(new Country<Integer>(1, "Belarus", null, null)),
				Optional.of(new Country<Integer>(2, "Usa", null, null)),
				Optional.of(new Country<Integer>(3, "France", null, null)),
				Optional.of(new Country<Integer>(4, "Italy", null, null))
		));
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void deleteById () {
		Country<Integer> expected = new Country<>(1, "", null, null);
		repository.delete(expected);
		
		Assert.assertEquals(Optional.empty(), repository.read(1));
	}
	
	@Test
	public void updateTest () {
		Country expected = new Country<>(1, "newName", null, null);
		repository.update(expected);
		Country actual = repository.read(1).get();
		
		Assert.assertEquals(expected, actual);
	}
}
