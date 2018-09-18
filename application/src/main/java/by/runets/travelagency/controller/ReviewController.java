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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ReviewController {
	@Autowired
	private IReviewService reviewService;
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping(value = "/user/review/{tourId}/tour")
	public String addReview (@ModelAttribute ReviewDTO reviewDTO, @PathVariable String tourId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String username = auth.getName();
		Review review = modelMapper.map(reviewDTO, Review.class);
		reviewService.createReviewByUsernameAndTourId(username, Long.parseLong(tourId), review);
		return "redirect:/tour/{tourId}/info";
	}
	
	@PostMapping(value = "/review/{reviewId}/delete/tour/{tourId}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteReview (@PathVariable String reviewId, @PathVariable String tourId) {
		Review review = new Review(Long.parseLong(reviewId));
		reviewService.delete(review);
		return "redirect:/tour/{tourId}/info";
	}
	
	@PostMapping(value = "/review/{reviewId}/edit/tour/{tourId}/user/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public String editReview (@ModelAttribute ReviewDTO reviewDTO, @PathVariable String reviewId, @PathVariable String tourId, @PathVariable String userId) {
		Review review = modelMapper.map(reviewDTO, Review.class);
		User user = new User();
		user.setId(Long.parseLong(userId));
		
		review.setId(Long.parseLong(reviewId));
		review.setTour(new Tour(Long.parseLong(tourId)));
		review.setUser(user);
		
		reviewService.update(review);
		return "redirect:/tour/{tourId}/info";
	}
}
