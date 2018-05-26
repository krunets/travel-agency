package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.repository.impl.CountryRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CountryRepositoryTest {
	@Test
	public void test() {
		IRepository<Country, Integer> repository = new CountryRepository();
		List<Country> actual = repository.readAll().get();
		Assert.assertNotNull(actual);
	}
}
