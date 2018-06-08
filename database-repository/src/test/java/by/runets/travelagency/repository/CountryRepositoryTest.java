package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.repository.impl.CountryRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

public class CountryRepositoryTest {
	private IRepository<Country, Integer> repository;
	private EmbeddedDatabase db;
	
	@Before
	public void setup() {
		db = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("db/schema.sql")
				.addScript("db/init-data.sql")
				.build();
		repository = new CountryRepository(new JdbcTemplate(db));
	}
	
	@Test
	public void testReadById() {
/*		Country<Integer> expected = new Country<Integer>(1, "Belarus", null, null);
		repository.create(expected);*/
		Optional<Country> actual = repository.read(1);
		System.out.println(actual);
		
/*
		System.out.println(expected);
*/
		
	/*	Assert.assertEquals(actual.getId(), expected.getId());
		Assert.assertEquals(actual.getName(), expected.getName());*/
	}
	
	@Test
	public void deleteById() {
		Country<Integer> expected = new Country<Integer>(1, "Belarus", null, null);
		repository.create(expected);
		
		System.out.println(repository.read(1));
		
		repository.delete(expected);
		
		System.out.println(repository.read(1));
	}
	
}
