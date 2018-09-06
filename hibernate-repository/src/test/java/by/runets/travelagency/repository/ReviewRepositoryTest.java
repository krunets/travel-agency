package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.util.config.DevelopmentDatabaseBeanConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@SqlGroup(
		@Sql(
				executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
				scripts = {"classpath:db/init-data.sql"}
		))
public class ReviewRepositoryTest {
	@Autowired
	private IDatabaseRepository<Review, Long> reviewRepository;
	
	@Test
	public void testReadById () {
		final long id = 1;
		final Optional<Review> expected = Optional.of(new Review(id, "Content 1", null, null));
		final Optional<Review> actual = reviewRepository.read(Review.class, id);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadAll () {
		final List<Optional<Review>> expected =
				new ArrayList<>(
						Arrays.asList(
								Optional.of(new Review(1, "Content 1", null, null)),
								Optional.of(new Review(2, "Content 2", null, null)),
								Optional.of(new Review(3, "Content 3", null, null))));
		final List<Optional<Review>> actual = reviewRepository.readAll(Review.class, DEFAULT_PAGINATION_SIZE);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testDeleteById () {
		final long id = 1;
		final Optional<Review> expected = reviewRepository.read(Review.class, id);
		Assert.assertNotNull(expected);
		
		reviewRepository.delete(expected.get());
		
		final Optional<Review> actual = reviewRepository.read(Review.class, id);
		
		Assert.assertEquals(Optional.empty(), actual);
	}
	
	@Test
	public void testUpdate () {
		final long id = 1;
		final Review expected = reviewRepository.read(Review.class, id).get();
		
		expected.setContent("testContent");
		reviewRepository.update(expected);
		
		Review actual = reviewRepository.read(Review.class, id).get();
		
		Assert.assertEquals(actual, expected);
	}
}
