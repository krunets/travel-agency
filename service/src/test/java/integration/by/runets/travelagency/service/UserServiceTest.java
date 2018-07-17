package integration.by.runets.travelagency.service;

import by.runets.travelagency.entity.User;
import by.runets.travelagency.service.impl.UserService;
import integration.by.runets.travelagency.config.DevelopmentDatabaseBeanConfig;
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
@ContextConfiguration(classes = DevelopmentDatabaseBeanConfig.class)
@ActiveProfiles(profiles = "development")
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/init-data.sql"})
})
public class UserServiceTest {
	@Autowired
	private UserService service;
	
	@Test
	public void testCreate () {
		User expected = new User(10, "testLogin", "testPassword", null, null);
		final long id = service.create(expected);
		User actual = service.read(id);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadById () {
		final long id = 1;
		User expected = new User(1, "root", "root", null, null);
		User actual = service.read(id);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadAll () {
		List<User> expected =
				new ArrayList<>(
						Arrays.asList(
								new User(1, "root", "root", null, null),
								new User(2, "admin", "admin", null, null),
								new User(3, "traveler1", "traveler1", null, null)));
		List<User> actual = service.readAll();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testDelete () {
		final long id = 1;
		User expected = service.read(id);
		Assert.assertNotNull(expected);
		service.delete(expected);
		User actual = service.read(id);
		
		Assert.assertNull(actual);
	}
	
	@Test
	public void testUpdate () {
		final long id = 1;
		User expected = service.read(id);
		
		expected.setLogin("newTestLogin");
		expected.setPassword("newTestPassword");
		
		service.update(expected);
		
		User actual = service.read(id);
		
		Assert.assertEquals(expected, actual);
	}
}
