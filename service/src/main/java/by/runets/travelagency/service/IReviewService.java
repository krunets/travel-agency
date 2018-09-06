package by.runets.travelagency.service;

public interface IReviewService<T, K> extends IService<T, K> {
	K createReviewByUsernameAndTourId (String username, Long tourId, T review);
}