package integration.by.runets.travelagency.service;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.service.IService;
import integration.by.runets.travelagency.config.DevelopmentDatabaseBeanConfig;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "development")
@ContextConfiguration(classes = DevelopmentDatabaseBeanConfig.class)
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/init-data.sql"})
})
public class CountryServiceTest {
	@Autowired
	private IService<Country, Long> countryService;
	
	@Test
	public void testCreate () {
		Country expected = new Country(1, "BY", null, null);
		final long id = countryService.create(expected);
		Country actual = countryService.read(id);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void  testReadAll () {
		List<Country> actual = countryService.readAll();
		
		List<Country> expected =
				new ArrayList<>(
						Arrays.asList(
								new Country(1, "BY", null, null),
								new Country(2, "US", null, null),
								new Country(3, "FR", null, null),
								new Country(4, "IT", null, null)));
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadById () {
		final long id = 1;
		Country expected = new Country(1, "BY", null, null);
		Country actual = countryService.read(id);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdate () {
		final long id = 1;
		Country expected = new Country(id, "newName", null, null);
		countryService.update(expected);
		Country actual = countryService.read(id);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testDelete () {
		final long id = 1;
		Country expected = new Country(id, "", null, null);
		countryService.delete(expected);
		
		Assert.assertNull(countryService.read(id));
	}
}
