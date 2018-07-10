package integration.by.runets.travelagency.service;

import by.runets.travelagency.entity.User;
import by.runets.travelagency.service.impl.UserService;
import integration.by.runets.travelagency.config.IntegrationServiceTestConfig;
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

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = IntegrationServiceTestConfig.class)
@ActiveProfiles(profiles = "development")
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/schema.sql", "classpath:db/init-data.sql"}),
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/drop.sql")
})
public class UserServiceTest {
	@Autowired
	private UserService service;

	@Test
	public void testCreate() {
		User expected = new User<>(10, "testLogin", "testPassword", null, null);
		service.create(expected);
		User actual = service.read(10);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadById() {
		User expected = new User<Integer>(1, "root", "root", null, null);
		User actual = service.read(1);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadAll() {
		List<User> expected =
				new ArrayList<>(
						Arrays.asList(
								new User<>(1, "root", "root", null, null),
								new User<>(2, "admin", "admin", null, null),
								new User<>(3, "traveler1", "traveler1", null, null)));
		List<User> actual = service.readAll();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testDelete() {
		User expected = service.read(1);
		Assert.assertNotNull(expected);
		service.delete(expected);
		User actual = service.read(1);
		
		Assert.assertNull(actual);
	}
	
	@Test
	public void testUpdate() {
		User<Integer> expected = service.read(1);
		
		expected.setLogin("newTestLogin");
		expected.setPassword("newTestPassword");
		
		service.update(expected);
		
		User<Integer> actual = service.read(1);
		
		Assert.assertEquals(expected, actual);
	}
}
