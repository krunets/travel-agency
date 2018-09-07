package by.runets.travelagency.controller;

import by.runets.travelagency.dto.ReviewDTO;
import by.runets.travelagency.entity.Review;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.service.IReviewService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ReviewController {
	@Autowired
	private IReviewService<Review, Long> reviewService;
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping(value = "/review/{reviewId}/delete/tour/{tourId}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteReview (@PathVariable String reviewId, @PathVariable String tourId) {
		Review review = new Review(Long.valueOf(reviewId));
		reviewService.delete(review);
		return "redirect:/tour/{tourId}/info";
	}
	
	@PostMapping(value = "/review/{reviewId}/edit/tour/{tourId}/user/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public String editReview (@ModelAttribute ReviewDTO reviewDTO, @PathVariable String reviewId, @PathVariable String tourId, @PathVariable String userId) {
		Review review = modelMapper.map(reviewDTO, Review.class);
		User user = new User();
		user.setId(Long.valueOf(userId));
		
		review.setId(Long.valueOf(reviewId));
		review.setTour(new Tour(Long.valueOf(tourId)));
		review.setUser(user);
		
		reviewService.update(review);
		return "redirect:/tour/{tourId}/info";
	}
}
