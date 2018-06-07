package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.repository.impl.ReviewRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReviewRepositoryTest {
	private final IRepository<Review, Integer> repository = new ReviewRepository();
	
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
}
