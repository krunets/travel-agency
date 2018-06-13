package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.joiner.impl.UserJoiner;
import by.runets.travelagency.repository.impl.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

public class UserRepositoryTest {
  private GenericXmlApplicationContext ctx;
  private IRepository<User, Integer> repository;

  @Before
  public void setUp() {
    ctx = new GenericXmlApplicationContext();
    ctx.getEnvironment().setActiveProfiles("database", "development");
    ctx.load("database-bean-config.xml");
    ctx.refresh();
    repository = (IRepository<User, Integer>) ctx.getBean("userRepository");
  }

  @Test
  public void testCreate() {
    Optional<User> expected = Optional.of(new User<>(10, "testLogin", "testPassword", null, null));
    repository.create(expected.get());
    Optional<User> actual = repository.read(10);
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testReadById() {
    Optional<User> expected = Optional.of(new User<Integer>(1, "root", "root", null, null));
    Optional<User> actual = repository.read(1);

    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testReadAll() {
    List<Optional<User>> expected =
        new ArrayList<>(
            Arrays.asList(
                Optional.of(new User<Integer>(1, "root", "root", null, null)),
                Optional.of(new User<Integer>(2, "admin", "admin", null, null)),
                Optional.of(new User<Integer>(3, "traveler1", "traveler1", null, null))));
    List<Optional<User>> actual = repository.readAll();

    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testDelete() {
    Optional<User> expected = repository.read(1);
    Assert.assertNotNull(expected);
    repository.delete(expected.get());
    Optional<User> actual = repository.read(1);

    Assert.assertEquals(Optional.empty(), actual);
  }

  @Test
  public void testUpdate() {
    User<Integer> expected = repository.read(1).get();

    expected.setLogin("newTestLogin");
    expected.setPassword("newTestPassword");

    repository.update(expected);

    User<Integer> actual = repository.read(1).get();

    Assert.assertEquals(expected, actual);
  }

  @After
  public void tearDown() {
    ctx.close();
  }
}
