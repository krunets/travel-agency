package by.runets.travelagency.repository;

import by.runets.travelagency.entity.User;
import by.runets.travelagency.repository.impl.UserRepository;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DevelopmentDatabaseBeanConfig.class)
@ActiveProfiles(profiles = "development")
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/schema.sql", "classpath:db/init-data.sql"}),
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/drop.sql")
})
public class UserRepositoryTest {
	@Autowired
	@Qualifier("UserRepository")
	private UserRepository userRepository;
	
	@Test
	public void testCreate () {
		Optional<User> expected = Optional.of(new User(10, "testLogin", "testPassword", null, null));
		userRepository.create(expected.get());
		Optional<User> actual = userRepository.read(10);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadById () {
		Optional<User> expected = Optional.of(new User(1, "root", "root", null, null));
		Optional<User> actual = userRepository.read(1);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadAll () {
		List<Optional<User>> expected =
				new ArrayList<>(
						Arrays.asList(
								Optional.of(new User(1, "root", "root", null, null)),
								Optional.of(new User(2, "admin", "admin", null, null)),
								Optional.of(new User(3, "traveler1", "traveler1", null, null))));
		List<Optional<User>> actual = userRepository.readAll();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testDelete () {
		Optional<User> expected = userRepository.read(1);
		Assert.assertNotNull(expected);
		userRepository.delete(expected.get());
		Optional<User> actual = userRepository.read(1);
		
		Assert.assertEquals(Optional.empty(), actual);
	}
	
	@Test
	public void testUpdate () {
		User expected = userRepository.read(1).get();
		
		expected.setLogin("newTestLogin");
		userRepository.update(expected);
		
		User actual = userRepository.read(1).get();
		
		Assert.assertEquals(expected, actual);
	}
	
}
