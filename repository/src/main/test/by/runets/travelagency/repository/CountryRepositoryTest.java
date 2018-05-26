package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.repository.impl.CountryRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CountryRepositoryTest {
	private final IRepository<Country, Integer> repository = new CountryRepository();
	
	@Test
	public void testReadAll() {
		List<Optional<Country>> expected = repository.readAll();
		List<Optional<Country>> actual = new ArrayList<>(Arrays.asList(
				Optional.of(new Country<Integer>(1, "Belarus")),
				Optional.of(new Country<Integer>(2, "USA")),
				Optional.of(new Country<Integer>(3, "France")),
				Optional.of(new Country<Integer>(4, "Italy"))
		));
		
		Assert.assertEquals(actual, expected);
	}
	
	
	@Test
	public void testReadById() {
		Optional<Country> expected = repository.read(1);
		Optional<Country> actual = Optional.of(new Country<Integer>(1, "Belarus"));
		
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testUpdate() {
		List<Optional<Country>> actual = new ArrayList<>(Arrays.asList(
				Optional.of(new Country<Integer>(1, "Belarus1")),
				Optional.of(new Country<Integer>(2, "USA")),
				Optional.of(new Country<Integer>(3, "France")),
				Optional.of(new Country<Integer>(4, "Italy"))
		));
		Country countryToUpdate = new Country<Integer>(1, "Belarus1");
		repository.update(countryToUpdate);
		
		List<Optional<Country>> expected = repository.readAll();
		System.out.println(expected);
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testDelete() {
		List<Optional<Country>> actual = new ArrayList<>(Arrays.asList(
				Optional.of(new Country<Integer>(1, "Belarus")),
				Optional.of(new Country<Integer>(2, "USA")),
				Optional.of(new Country<Integer>(3, "France"))
		));
		
		Country countryToDelete = new Country<Integer>(4, "Italy");
		repository.delete(countryToDelete);
		
		List<Optional<Country>> expected = repository.readAll();
		Assert.assertEquals(actual, expected);
	}
	
}
