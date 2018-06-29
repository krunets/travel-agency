package integration.by.runets.travelagency.service;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.service.impl.CountryService;
import integration.by.runets.travelagency.config.IntegrationServiceTestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "development")
@ContextConfiguration(classes = IntegrationServiceTestConfig.class)
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/schema.sql", "classpath:db/init-data.sql"}),
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/drop.sql")
})
public class CountryServiceTest {
	@Autowired
	private CountryService countryService;
	@Test
	public void testCreate() {
		Country<Integer> expected = new Country<Integer>(1, "Belarus", null, null);
		Country actual = countryService.read(1);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadAll() {
		List<Country> actual = countryService.readAll();
		
		List<Country> expected =
				new ArrayList<>(
						Arrays.asList(
								new Country<>(1, "Belarus", null, null),
								new Country<>(2, "Usa", null, null),
								new Country<>(3, "France", null, null),
								new Country<>(4, "Italy", null, null)));
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadById() {
		Country<Integer> expected = new Country<>(1, "Belarus", null, null);
		Country actual = countryService.read(1);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdate() {
		Country expected = new Country<>(1, "newName", null, null);
		countryService.update(expected);
		Country actual = countryService.read(1);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testDelete() {
		Country<Integer> expected = new Country<>(1, "", null, null);
		countryService.delete(expected);
		
		Assert.assertNull(countryService.read(1));
	}
}
