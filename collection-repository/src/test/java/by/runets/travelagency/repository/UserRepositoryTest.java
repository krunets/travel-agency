package by.runets.travelagency.repository;

import by.runets.travelagency.entity.User;
import by.runets.travelagency.repository.impl.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Deprecated
public class UserRepositoryTest {
	private GenericXmlApplicationContext ctx;
	private ICollectionRepository<User, Integer> repository = new UserRepository();
	
	@Before
	public void setUp() {
		ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles("collection");
		ctx.load("collection-bean-config.xml");
		ctx.refresh();
		repository = (ICollectionRepository<User, Integer>) ctx.getBean("userRepository");
	}
	
	@Test
	public void testReadAll() {
		List<Optional<User>> actual = new ArrayList<>(Arrays.asList(
				Optional.of(new User<Integer>(1, "root", "root", null, null)),
				Optional.of(new User<Integer>(2, "admin", "admin", null, null)),
				Optional.of(new User<Integer>(3, "traveler1", "traveler1", null, null))
				));
		
		List<Optional<User>> expected = repository.readAll();
		
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testReadById() {
		Optional<User> expected = repository.read(1);
		Optional<User> actual = Optional.of(new User<Integer>(1, "root", "root", null, null));
		
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testDelete() {
		List<Optional<User>> actual = new ArrayList<>(Arrays.asList(
				Optional.of(new User<Integer>(1, "root", "root", null, null)),
				Optional.of(new User<Integer>(3, "traveler1", "traveler1", null, null))
		));
		
		User<Integer> entity = new User<Integer>(2, "admin", "admin", null, null);
		repository.delete(entity);
		
		List<Optional<User>> expected = repository.readAll();
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testUpdate() {
		List<Optional<User>> actual = new ArrayList<>(Arrays.asList(
				Optional.of(new User<Integer>(1, "root2", "root23", null, null)),
				Optional.of(new User<Integer>(3, "traveler1", "traveler1", null, null))
		));
		
		User<Integer> entityToUpdate = new User<Integer>(1, "root2", "root23", null, null);
		repository.update(entityToUpdate);
		
		List<Optional<User>> expected = repository.readAll();
		Assert.assertEquals(actual, expected);
	}
	
	@After
	public void tearDown() {
		ctx.close();
	}
}
