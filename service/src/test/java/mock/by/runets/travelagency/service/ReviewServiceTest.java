package mock.by.runets.travelagency.service;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.hibernate.impl.ReviewRepository;
import by.runets.travelagency.service.impl.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static integration.by.runets.travelagency.config.DevelopmentDatabaseBeanConfig.DEFAULT_PAGINATION_SIZE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ReviewServiceTest {
  @Mock
  private ReviewRepository repository;
  @InjectMocks
  private ReviewService service;

  @Test
  public void testReadAll() {
	when(repository.readAll(Review.class, DEFAULT_PAGINATION_SIZE)).thenReturn(new ArrayList<>());

	assertThat(service.readAll(DEFAULT_PAGINATION_SIZE), is(notNullValue()));
  }

  @Test
  public void testCreate() {
	Review review = new Review(5, "Content 1", null, null);

	service.create(review);
	verify(repository, times(1)).create(review);
  }

  @Test
  public void testUpdate() {
	Review review = new Review(1, "Content 1", null, null);

	service.update(review);
	verify(repository, times(1)).update(review);
  }

  @Test
  public void testDelete() {
	Review review = new Review(1, "Content 1", null, null);

	service.delete(review);
	verify(repository, times(1)).delete(review);
  }
}
