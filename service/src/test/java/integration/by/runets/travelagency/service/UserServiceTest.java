package integration.by.runets.travelagency.service;

import by.runets.travelagency.entity.Role;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.service.IService;
import by.runets.travelagency.service.IUserService;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static integration.by.runets.travelagency.config.DevelopmentDatabaseBeanConfig.DEFAULT_PAGINATION_SIZE;

@Transactional
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DevelopmentDatabaseBeanConfig.class)
@ActiveProfiles(profiles = "development")
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/init-data.sql"})
})
public class UserServiceTest {
	private static final String NAMED_QUERY = "FIND_BY_LOGIN";
	private static final String FIELD = "login";
	
	@Autowired
	private IUserService<User, Long> userService;
	
	@Test
	public void testCreate () {
		User expected = new User(10, "testLogin", "testPassword", null, null, Role.ADMIN);
		final long id = userService.create(expected);
		User actual = userService.read(id);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadById () {
		final long id = 1;
		User expected = new User(1, "root", "root", null, null, Role.ADMIN);
		User actual = userService.read(id);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadAll () {
		List<User> expected =
				new ArrayList<>(
						Arrays.asList(
								new User(1, "root", "root", null, null, Role.ADMIN),
								new User(2, "admin", "admin", null, null, Role.ADMIN),
								new User(3, "traveler1", "traveler1", null, null, Role.MEMBER)));
		List<User> actual = userService.readAll(DEFAULT_PAGINATION_SIZE);
		log.error(actual + "");
		Assert.assertEquals(expected, actual);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testDelete () {
		final long id = 1;
		User expected = userService.read(id);
		Assert.assertNotNull(expected);
		userService.delete(expected);
		User actual = userService.read(id);
		
		Assert.assertNull(actual);
	}
	
	@Test
	public void testUpdate () {
		final long id = 1;
		User expected = userService.read(id);
		
		expected.setLogin("newTestLogin");
		expected.setPassword("newTestPassword");
		
		userService.update(expected);
		
		User actual = userService.read(id);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testRegisterExistUser() {
		User user = new User();
		user.setLogin("admin");
		
		Assert.assertFalse(userService.registerUserAccount(user));
	}
	
	
	@Test
	public void testRegisterNotExistUser() {
		User expected = new User(10, "testLogin", "testPassword", null, null, Role.MEMBER);
		Assert.assertTrue(userService.registerUserAccount(expected));
		
		User actual = userService.readAllByField(NAMED_QUERY, FIELD, "testLogin", DEFAULT_PAGINATION_SIZE).get(0);
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testReadUserByRole() {
		List<User> expected =
				new ArrayList<>(
						Arrays.asList(
								new User(3, "traveler1", "traveler1", null, null, Role.MEMBER)));
		List<User> actual = userService.readUserByRole();
		Assert.assertEquals(actual, expected);
	}
}
