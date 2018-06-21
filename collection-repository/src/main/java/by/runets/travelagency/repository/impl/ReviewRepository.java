package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Is inherited from common class and provides list of reviews.
 */
@Deprecated
public class ReviewRepository extends AbstractRepository<Review, Integer> {
	private static List<Review> reviews = new ArrayList<>(Arrays.asList(
			new Review<>(1, "Content 1", null),
			new Review<>(2, "Content2", null),
			new Review<>(3, "Content3", null),
			new Review<>(4, "Content4", null),
			new Review<>(5, "Content5", null)));
	@Deprecated
	public ReviewRepository () {
		super(reviews);
	}
}
