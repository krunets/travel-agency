package by.runets.travelagency.service;

import by.runets.travelagency.entity.Review;

public interface IReviewService extends IService<Review, Long> {
  Long createReviewByUsernameAndTourId(String username, Long tourId, Review review);
}