package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.service.IReviewService;
import by.runets.travelagency.service.ITourService;
import by.runets.travelagency.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static by.runets.travelagency.util.constant.NamedQueryConstant.FIND_BY_LOGIN;
import static by.runets.travelagency.util.constant.NamedQueryConstant.LOGIN_FIELD;
import static by.runets.travelagency.util.constant.PaginationConstant.DEFAULT_USER_PAGINATION;

@Service
public class ReviewService extends AbstractService<Review, Long> implements IReviewService<Review, Long> {
	@Autowired
	private IDatabaseRepository<Review, Long> abstractRepository;
	@Autowired
	private IUserService<User, Long> userService;
	@Autowired
	private ITourService<Tour, Long> tourService;
	
	public ReviewService (Class<Review> classType, IDatabaseRepository<Review, Long> abstractRepository) {
		super(classType, abstractRepository);
		this.abstractRepository = abstractRepository;
	}
	
	@Override
	@Transactional
	public Long createReviewByUsernameAndTourId (String username, Long tourId, Review review) {
		User user = userService.readAllByField(FIND_BY_LOGIN, LOGIN_FIELD, username, DEFAULT_USER_PAGINATION).get(0);
		Tour tour = tourService.read(tourId);
		review.setUser(user);
		review.setTour(tour);
		return abstractRepository.create(review);
	}
}
