package integration.by.runets.travelagency.service;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.service.impl.ReviewService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DevelopmentDatabaseBeanConfig.class)
@ActiveProfiles(profiles = "development")
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/init-data.sql"})
})
public class ReviewServiceTest {
	@Autowired
	private ReviewService service;
	
	@Test
	public void testReadById () {
		final long id = 1;
		Review expected = new Review(id, "Content 1", null);
		Review actual = service.read(id);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadAll () {
		List<Review> expected =
				new ArrayList<>(
						Arrays.asList(
								new Review(1, "Content 1", null),
								new Review(2, "Content 2", null),
								new Review(3, "Content 3", null)));
		List<Review> actual = service.readAll();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testCreate () {
		Review expected =
				new Review(10, "testContent", new User(1, null, null, null, null));
		final long id = service.create(expected);
		Review actual = service.read(id);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdate () {
		final long id = 1;
		Review expected = service.read(id);
		
		expected.setContent("testContent");
		service.update(expected);
		
		Review actual = service.read(id);
		
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testDelete () {
		final long id = 1;
		Review expected = service.read(id);
		Assert.assertNotNull(expected);
		
		service.delete(expected);
		
		Review actual = service.read(id);
		
		Assert.assertNull(actual);
	}
}
