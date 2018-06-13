package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Review;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReviewRepositoryTest {
	private GenericXmlApplicationContext ctx;
	private IRepository<Review, Integer> repository;
	
	@Before
	public void setUp() {
		ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles("collection");
		ctx.load("collection-bean-config.xml");
		ctx.refresh();
		repository = (IRepository<Review, Integer>) ctx.getBean("reviewRepository");
	}
	
	@Test
	public void readAllTest () {
		List<Optional<Review>> actual = new ArrayList<>(Arrays.asList(
				Optional.of(new Review<Integer>(1, "Content 1", null)),
				Optional.of(new Review<Integer>(2, "Content2", null)),
				Optional.of(new Review<Integer>(3, "Content3", null)),
				Optional.of(new Review<Integer>(4, "Content4", null)),
				Optional.of(new Review<Integer>(5, "Content5", null))));
		
		List<Optional<Review>> expected = repository.readAll();
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void testReadById() {
		Optional<Review> expected = repository.read(1);
		Optional<Review> actual = Optional.of(new Review<Integer>(1, "Content 1", null));
		
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testDelete () {
		List<Optional<Review>> actual = new ArrayList<>(Arrays.asList(
				Optional.of(new Review<Integer>(1, "Content 1", null)),
				Optional.of(new Review<Integer>(2, "Content2", null)),
				Optional.of(new Review<Integer>(3, "Content3", null)),
				Optional.of(new Review<Integer>(5, "Content5", null))));
		
		Review entityToDelete = new Review<Integer>(4, "Content4", null);
		repository.delete(entityToDelete);
		
		List<Optional<Review>> expected = repository.readAll();
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testUpdate() {
		List<Optional<Review>> actual = new ArrayList<>(Arrays.asList(
				Optional.of(new Review<Integer>(1, "Content 11", null)),
				Optional.of(new Review<Integer>(2, "Content2", null)),
				Optional.of(new Review<Integer>(3, "Content3", null)),
				Optional.of(new Review<Integer>(5, "Content5", null))));
		
		Review entityToUpdate = new Review<Integer>(1, "Content 11", null);
		repository.update(entityToUpdate);
		
		List<Optional<Review>> expected = repository.readAll();
		Assert.assertEquals(actual, expected);
	}
	
	@After
	public void tearDown() {
		ctx.close();
	}
}
