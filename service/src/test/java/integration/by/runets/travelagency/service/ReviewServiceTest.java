package integration.by.runets.travelagency.service;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.service.impl.ReviewService;
import integration.by.runets.travelagency.config.IntegrationServiceTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = IntegrationServiceTestConfig.class)
@ActiveProfiles(profiles = "development")
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/schema.sql", "classpath:db/init-data.sql"}),
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/drop.sql")
})
public class ReviewServiceTest {
	@Autowired
	private ReviewService service;
	
	@Test
	public void testReadById() {
		Review<Integer> expected = new Review<Integer>(1, "Content 1", null);
		Review actual = service.read(1);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadAll() {
		List<Review> expected =
				new ArrayList<>(
						Arrays.asList(
								new Review<Integer>(1, "Content 1", null),
								new Review<Integer>(2, "Content 2", null),
								new Review<Integer>(3, "Content 3", null)));
		List<Review> actual = service.readAll();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testCreate() {
		Review<Integer> expected =
				new Review<>(10, "testContent", new User<Integer>(1, null, null, null, null));
		service.create(expected);
		Review<Integer> actual = service.read(expected.getId());
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdate() {
		Review expected = service.read(1);
		
		expected.setContent("testContent");
		service.update(expected);
		
		Review actual = service.read(1);
		
		Assert.assertEquals(actual, expected);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testDelete() {
		Review expected = service.read(1);
		Assert.assertNotNull(expected);
		
		service.delete(expected);
		
		Review actual = service.read(1);
		
		Assert.assertNull(actual);
	}
}
