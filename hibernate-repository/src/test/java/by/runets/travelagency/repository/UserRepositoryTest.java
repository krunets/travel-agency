package by.runets.travelagency.repository;


import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.entity.Review;
import by.runets.travelagency.entity.Role;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.util.config.DevelopmentDatabaseBeanConfig;
import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.extern.log4j.Log4j2;
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

import java.util.*;

import static by.runets.travelagency.util.config.DevelopmentDatabaseBeanConfig.DEFAULT_PAGE;
import static by.runets.travelagency.util.config.DevelopmentDatabaseBeanConfig.DEFAULT_PAGINATION_SIZE;
import static by.runets.travelagency.util.constant.NamedQueryConstant.FIND_USER_WITH_HOTELS;
import static by.runets.travelagency.util.constant.NamedQueryConstant.LOGIN_FIELD;
import static by.runets.travelagency.util.constant.PaginationConstant.DEFAULT_USER_PAGINATION;

@Log4j2
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
  @Autowired
  private IDatabaseRepository<Hotel, Long> hotelRepository;

  @Test
  public void testCreate() {
	final Optional<User> expected = Optional.of(new User(10, "testLogin", "testPassword", Role.MEMBER));
	final long id = userRepository.create(expected.get());
	final Optional<User> actual = userRepository.read(User.class, id);
	Assert.assertEquals(expected, actual);
  }

  @Test
  public void testReadById() {
	final long id = 1;
	final Optional<User> expected = Optional.of(new User(id, "root", "root", Role.ADMIN));
	final Optional<User> actual = userRepository.read(User.class, id);

	Assert.assertEquals(expected, actual);
  }

  @Test
  public void testReadAll() {
	final List<Optional<User>> expected =
		new ArrayList<>(
			Arrays.asList(
				Optional.of(new User(1, "root", "root", Role.ADMIN)),
				Optional.of(new User(2, "admin", "admin", Role.ADMIN)),
				Optional.of(new User(3, "traveler1", "traveler1", Role.MEMBER))));
	final List<Optional<User>> actual = userRepository.readAll(User.class, DEFAULT_PAGINATION_SIZE);

	Assert.assertEquals(expected, actual);
  }

  @Test
  public void testDelete() {
	final long id = 1;
	final Optional<User> expected = userRepository.read(User.class, id);
	userRepository.delete(expected.get());
	final Optional<User> actual = userRepository.read(User.class, id);

	Assert.assertEquals(Optional.empty(), actual);
  }

  @Test
  public void testUpdate() {
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
	final Optional<User> expected = Optional.of(new User(2, "admin", "admin", Role.ADMIN));
	final Optional<User> actual = userRepository.readByNameQuery("FIND_BY_LOGIN", "login", "admin", DEFAULT_PAGE, DEFAULT_PAGINATION_SIZE).get(0);
	Assert.assertEquals(expected, actual);
  }

  @Test
  public void testBookHotel() {
	Hotel marriot = new Hotel(1, "Marriot", "123 23 23", 5, 53.932717, 27.511248);
	User user = new User(6, "root", "root", Role.ADMIN);

	Set<Hotel> expected = new HashSet<>();
	expected.add(marriot);

	user.setHotels(expected);

	Long aLong = userRepository.create(user);

	Optional<User> dbUser = userRepository.read(User.class, aLong);

	Assert.assertEquals(expected, dbUser.get().getHotels());
  }


  @Test
  public void testUpdateHotel() {
	Hotel marriot = new Hotel(1, "Marriot", "123 23 23", 5, 53.932717, 27.511248);
	Optional<User> read = userRepository.read(User.class, Long.parseLong("1"));

	read.get().getHotels().add(marriot);

	userRepository.update(read.get());

	Optional<User> read2 = userRepository.read(User.class, Long.parseLong("1"));

    System.out.println(read2.get().getHotels());

	Assert.assertEquals(read.get().getHotels(), read2.get().getHotels());
  }

}
