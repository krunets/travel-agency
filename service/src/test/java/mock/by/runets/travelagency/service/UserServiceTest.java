package mock.by.runets.travelagency.service;

import by.runets.travelagency.entity.Role;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.hibernate.impl.UserRepository;
import by.runets.travelagency.service.impl.UserService;
import integration.by.runets.travelagency.config.DevelopmentDatabaseBeanConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Optional;

import static integration.by.runets.travelagency.config.DevelopmentDatabaseBeanConfig.DEFAULT_PAGINATION_SIZE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
@ContextConfiguration(classes = DevelopmentDatabaseBeanConfig.class)
@ActiveProfiles(profiles = "development")

public class UserServiceTest {
  @Mock
  private UserRepository repository;
  private UserService service;

  @Before
  public void setUp() {
	MockitoAnnotations.initMocks(this);
	service = new UserService(User.class, repository);
  }

  @Test
  public void testReadAll() {
	when(repository.readAll(User.class, DEFAULT_PAGINATION_SIZE)).thenReturn(new ArrayList<Optional<User>>());

	assertThat(service.readAll(DEFAULT_PAGINATION_SIZE), is(notNullValue()));
  }

  @Test
  public void testCreate() {
	User user = new User(5, "admin", "admin", Role.ADMIN);

	service.create(user);
	verify(repository, times(1)).create(user);
  }

  @Test
  public void testUpdate() {
	User user = new User(2, "admin1", "admin1", Role.ADMIN);

	service.update(user);
	verify(repository, times(1)).update(user);
  }

  @Test
  public void testDelete() {
	User user = new User(2, "admin", "admin", Role.ADMIN);

	service.delete(user);
	verify(repository, times(1)).delete(user);
  }
}
