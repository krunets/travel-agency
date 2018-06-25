package by.runets.travelagency.service;

import by.runets.travelagency.config.ServiceTestConfig;
import by.runets.travelagency.entity.Review;
import by.runets.travelagency.repository.impl.ReviewRepository;
import by.runets.travelagency.service.impl.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ServiceTestConfig.class)
public class ReviewServiceTest {
	@Mock
	private ReviewRepository repository;
	@InjectMocks
	private ReviewService service;
	
	@Test
	public void testRead () {
		when(repository.read(anyInt())).thenReturn(Optional.of(new Review<Integer>()));
		
		assertThat(service.read(anyInt()), is(notNullValue()));
	}
	
	@Test
	public void testReadAll () {
		when(repository.readAll()).thenReturn(new ArrayList<Optional<Review>>());
		
		assertThat(service.readAll(), is(notNullValue()));
	}
	
	@Test
	public void testCreate() {
		Review<Integer> review = new Review<Integer>(5, "Content 1", null);
		
		service.create(review);
		verify(repository, times(1)).create(review);
	}
	
	@Test
	public void testUpdate() {
		Review<Integer> review = new Review<Integer>(1, "Content 1", null);
		
		service.update(review);
		verify(repository, times(1)).update(review);
	}
	
	@Test
	public void testDelete() {
		Review<Integer> review = new Review<Integer>(1, "Content 1", null);
		
		service.delete(review);
		verify(repository, times(1)).delete(review);
	}
}
