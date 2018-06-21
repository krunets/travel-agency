package by.runets.travelagency.service;

import by.runets.travelagency.entity.User;
import by.runets.travelagency.repository.ICollectionRepository;
import by.runets.travelagency.repository.impl.UserRepository;
import by.runets.travelagency.service.impl.UserService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class UserServiceTest {
	private final ICollectionRepository<User, Integer> repository = mock(UserRepository.class);
	private final IService<User, Integer> service = new UserService(repository);
	
	@Test
	public void testRead() {
		when(repository.read(anyInt())).thenReturn(Optional.of(new User()));
		
		assertThat(service.read(anyInt()), is(notNullValue()));
	}
	
	@Test
	public void testReadAll() {
		when(repository.readAll()).thenReturn(new ArrayList<Optional<User>>());
		
		assertThat(service.readAll(), is(notNullValue()));
	}
	
	@Test
	public void testCreate() {
		User<Integer> user = new User<Integer>(5, "admin", "admin", null, null);
		
		service.create(user);
		verify(repository, times(1)).create(user);
	}
	
	@Test
	public void testUpdate() {
		User<Integer> user = new User<Integer>(2, "admin1", "admin1", null, null);
		
		service.update(user);
		verify(repository, times(1)).update(user);
	}
	
	@Test
	public void testDelete() {
		User<Integer> user = new User<Integer>(2, "admin", "admin", null, null);
		
		service.delete(user);
		verify(repository, times(1)).delete(user);
	}
}
