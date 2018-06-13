package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Country;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CountryRepositoryTest {
	private IRepository<Country, Integer> repository;
	private GenericXmlApplicationContext ctx;

	@Before
	public void setUp() {
	  ctx = new GenericXmlApplicationContext();
	  ctx.getEnvironment().setActiveProfiles("collection");
	  ctx.load("collection-bean-config.xml");
	  ctx.refresh();
	  repository = (IRepository<Country, Integer>) ctx.getBean("countryRepository");
	}
	
	@Test
	public void testReadAll() {
		List<Optional<Country>> actual = repository.readAll();
		List<Optional<Country>> expected = new ArrayList<>(Arrays.asList(
				Optional.of(new Country<>(1, "Belarus", null, null)),
				Optional.of(new Country<>(2, "USA", null, null)),
				Optional.of(new Country<>(3, "France", null, null)),
				Optional.of(new Country<>(4, "Italy", null, null))
		));
		Assert.assertEquals(actual, expected);
	}
	
	
	@Test
	public void testReadById() {
		Optional<Country> expected = repository.read(1);
		Optional<Country> actual = Optional.of(new Country<>(1, "Belarus", null, null));
		
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testUpdate() {
		List<Optional<Country>> actual = new ArrayList<>(Arrays.asList(
				Optional.of(new Country<>(1, "Belarus1", null, null)),
				Optional.of(new Country<>(2, "USA", null, null)),
				Optional.of(new Country<>(3, "France", null, null))
		));
		Country countryToUpdate = new Country<>(1, "Belarus1", null, null);
		repository.update(countryToUpdate);
		
		List<Optional<Country>> expected = repository.readAll();
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testDelete() {
		List<Optional<Country>> expected = new ArrayList<>(Arrays.asList(
				Optional.of(new Country<>(1, "Belarus", null, null)),
				Optional.of(new Country<>(2, "USA", null, null)),
				Optional.of(new Country<>(3, "France", null, null))
		));
		
		Country countryToDelete = new Country<>(4, "Italy", null, null);
		repository.delete(countryToDelete);
		
		List<Optional<Country>> actual = repository.readAll();
		Assert.assertEquals(expected, actual);
	}
	@After
	public void tearDown() {
		ctx.close();
	}
}
