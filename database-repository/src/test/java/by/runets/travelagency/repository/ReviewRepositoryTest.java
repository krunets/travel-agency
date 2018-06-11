package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.repository.impl.ReviewRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

public class ReviewRepositoryTest {
	private IRepository<Review, Integer> repository;
	
	@Before
	public void setup () {
		EmbeddedDatabase db =
				new EmbeddedDatabaseBuilder()
						.generateUniqueName(true)
						.setType(H2)
						.setScriptEncoding("UTF-8")
						.ignoreFailedDrops(true)
						.addScript("db/schema.sql")
						.addScript("db/init-data.sql")
						.build();
		repository = new ReviewRepository(new JdbcTemplate(db));
	}
	
	@Test
	public void testCreate () {
		Review<Integer> expected = new Review<>(10, "testContent", new User<Integer>(1, null, null, null, null));
		repository.create(expected);
		Review<Integer> actual = repository.read(expected.getId()).get();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadById () {
		Optional<Review<Integer>> expected = Optional.of(new Review<Integer>(1, "Content 1", null));
		Optional<Review> actual = repository.read(1);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadAll() {
		List<Optional<Review>> expected = new ArrayList<>(Arrays.asList(
				Optional.of(new Review<Integer>(1, "Content 1", null)),
				Optional.of(new Review<Integer>(2, "Content 2", null)),
				Optional.of(new Review<Integer>(3, "Content 3", null))));
		List<Optional<Review>> actual = repository.readAll();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testDeleteById() {
		Optional<Review> expected = repository.read(1);
		Assert.assertNotNull(expected);
		
		repository.delete(expected.get());
		
		Optional<Review> actual = repository.read(1);
		
		Assert.assertEquals(Optional.empty(), actual);
	}
	
	@Test
	public void testUpdate() {
		Review<Integer> expected = repository.read(1).get();
		
		expected.setContent("testContent");
		repository.update(expected);
		
		Review<Integer> actual = repository.read(1).get();
		
		Assert.assertEquals(actual, expected);
	}
}
