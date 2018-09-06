package integration.by.runets.travelagency.service;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.service.IReviewService;
import by.runets.travelagency.service.IService;
import integration.by.runets.travelagency.config.DevelopmentDatabaseBeanConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static integration.by.runets.travelagency.config.DevelopmentDatabaseBeanConfig.DEFAULT_PAGINATION_SIZE;

@Transactional

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DevelopmentDatabaseBeanConfig.class)
@ActiveProfiles(profiles = "development")
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/init-data.sql"})
})
public class ReviewServiceTest {
	@Autowired
	private IReviewService<Review, Long> reviewService;
	
	@Test
	public void testReadById () {
		final long id = 1;
		Review expected = new Review(id, "Content 1", null, null);
		Review actual = reviewService.read(id);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadAll () {
		List<Review> expected =
				new ArrayList<>(
						Arrays.asList(
								new Review(1, "Content 1", null, null),
								new Review(2, "Content 2", null, null),
								new Review(3, "Content 3", null, null)));
		List<Review> actual = reviewService.readAll(DEFAULT_PAGINATION_SIZE);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testCreate () {
		Review expected =
				new Review(10, "testContent", null, null);
		final long id = reviewService.create(expected);
		Review actual = reviewService.read(id);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdate () {
		final long id = 1;
		Review expected = reviewService.read(id);
		
		expected.setContent("testContent");
		reviewService.update(expected);
		
		Review actual = reviewService.read(id);
		
		Assert.assertEquals(actual, expected);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testDelete () {
		final long id = 1;
		Review expected = reviewService.read(id);
		Assert.assertNotNull(expected);
		
		reviewService.delete(expected);
		
		Review actual = reviewService.read(id);
		
		Assert.assertNull(actual);
	}
	
	@Test
	public void testCreateReview() {
		Review review = new Review();
		review.setContent("newContent");
		Long id = reviewService.createReviewByUsernameAndTourId("root", 1L, review);
		System.out.println(reviewService.read(id));
		System.out.println(reviewService.readAll(20));
	}
}
