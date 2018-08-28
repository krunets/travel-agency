package by.runets.travelagency.repository;


import by.runets.travelagency.entity.Role;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.util.config.DevelopmentDatabaseBeanConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static by.runets.travelagency.util.config.DevelopmentDatabaseBeanConfig.DEFAULT_PAGINATION_SIZE;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "development")
@ContextConfiguration(classes = DevelopmentDatabaseBeanConfig.class)
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/init-data.sql"}),
})
public class UserRepositoryTest {
	@Autowired
	private IDatabaseRepository<User, Long> userRepository;

	@Test
	public void testCreate () {
		final Optional<User> expected = Optional.of(new User(10, "testLogin", "testPassword", null, null, Role.MEMBER));
		final long id = userRepository.create(expected.get());
		final Optional<User> actual = userRepository.read(User.class, id);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadById () {
		final long id = 1;
		final Optional<User> expected = Optional.of(new User(id, "root", "root", null, null, Role.ADMIN));
		final Optional<User> actual = userRepository.read(User.class, id);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadAll () {
		final List<Optional<User>> expected =
				new ArrayList<>(
						Arrays.asList(
								Optional.of(new User(1, "root", "root", null, null, Role.ADMIN)),
								Optional.of(new User(2, "admin", "admin", null, null, Role.ADMIN)),
								Optional.of(new User(3, "traveler1", "traveler1", null, null, Role.MEMBER))));
		final List<Optional<User>> actual = userRepository.readAll(User.class, DEFAULT_PAGINATION_SIZE);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testDelete () {
		final long id = 1;
		final Optional<User> expected = userRepository.read(User.class, id);
		userRepository.delete(expected.get());
		final Optional<User> actual = userRepository.read(User.class, id);
		
		Assert.assertEquals(Optional.empty(), actual);
	}
	
	@Test
	public void testUpdate () {
		final long id = 1;
		final User expected = userRepository.read(User.class, id).get();
		
		expected.setLogin("newTestLogin");
		expected.setPassword("newTestPassword");
		
		userRepository.update(expected);
		
		final User actual = userRepository.read(User.class, id).get();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void readByNameQuery() {
		final Optional<User> expected = Optional.of(new User(2, "admin", "admin", null, null, Role.ADMIN));
		final Optional<User> actual = userRepository.readByNameQuery("FIND_BY_LOGIN", "login", "admin", DEFAULT_PAGINATION_SIZE).get(0);
		Assert.assertEquals(expected, actual);
	}
}
