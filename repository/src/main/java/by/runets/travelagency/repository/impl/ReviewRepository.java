package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Is inherited from common class and provides list of reviews.
 */
public class ReviewRepository extends AbstractRepository<Review, Integer> {
	private static List<Review> reviews = new ArrayList<>(Arrays.asList(
			new Review<Integer>(1, "Content 1"),
			new Review<Integer>(2, "Content2"),
			new Review<Integer>(3, "Content3"),
			new Review<Integer>(4, "Content4"),
			new Review<Integer>(5, "Content5")));
	
	public ReviewRepository () {
		super(reviews);
	}
}
