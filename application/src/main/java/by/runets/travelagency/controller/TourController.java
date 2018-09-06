package by.runets.travelagency.controller;

import by.runets.travelagency.dto.CountryDTO;
import by.runets.travelagency.dto.PaginationDTO;
import by.runets.travelagency.dto.ReviewDTO;
import by.runets.travelagency.dto.SearchTourDTO;
import by.runets.travelagency.entity.Review;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.service.IReviewService;
import by.runets.travelagency.service.ITourService;
import by.runets.travelagency.util.converter.Converter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import static by.runets.travelagency.util.constant.NamedQueryConstant.FIND_TOUR_BY_ID_WITH_USER_REVIEWS;
import static by.runets.travelagency.util.constant.NamedQueryConstant.ID;
import static by.runets.travelagency.util.constant.PaginationConstant.DEFAULT_PAGINATION_SIZE;

@Slf4j
@Controller
public class TourController {
	@Autowired
	private ITourService<Tour, Long> tourService;
	@Autowired
	private Converter<LocalDate, String> dateConverter;
	@Autowired
	private List<CountryDTO> countryDTOs;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private IReviewService<Review, Long> reviewService;
	
	@PostMapping(value = "/tour/search")
	public String seacrhTour (@Valid @ModelAttribute SearchTourDTO searchTourDTO, Model model) {
		List<Tour> tours = tourService.findTourByCountryAndDateAndDuration(
				searchTourDTO.getCountryName(),
				dateConverter.convert(searchTourDTO.getStartTourDate()),
				Duration.ofDays(Long.parseLong(searchTourDTO.getTourDuration()))
		);
		model.addAttribute("checkTours", true);
		model.addAttribute("criteriaTour", tours);
		model.addAttribute("countriesDTO", countryDTOs);
		
		return "home";
	}
	
	@PostMapping(value = "/tour/pagination")
	public String paginateTour (@ModelAttribute("pagination") PaginationDTO paginationDTO, Model model) {
		List<Tour> tours = tourService.readAll(paginationDTO.getSize());
		model.addAttribute("checkTours", false);
		model.addAttribute("criteriaTour", "");
		model.addAttribute("tours", tours);
		model.addAttribute("countriesDTO", countryDTOs);
		return "home";
	}
	
	@GetMapping(value = "/tour/{tourId}/info")
	public String getTourInfo (@PathVariable String tourId, Model model) {
		List<Tour> tours = tourService.readAllByField(FIND_TOUR_BY_ID_WITH_USER_REVIEWS, ID, Long.valueOf(tourId), DEFAULT_PAGINATION_SIZE);
		if (!tours.isEmpty()) {
			model.addAttribute("countriesDTO", countryDTOs);
			model.addAttribute("tour", tours.get(0));
		}
		return "tourpage";
	}
	
	@PostMapping(value = "/user/review/{tourId}/tour")
	public String review (@ModelAttribute ReviewDTO reviewDTO, @PathVariable String tourId, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String username = auth.getName();
		Review review = modelMapper.map(reviewDTO, Review.class);
		
		reviewService.createReviewByUsernameAndTourId(username, Long.valueOf(tourId), review);
		Tour tour = tourService.readAllByField(FIND_TOUR_BY_ID_WITH_USER_REVIEWS, ID, Long.valueOf(tourId), DEFAULT_PAGINATION_SIZE).get(0);
		
		model.addAttribute("countriesDTO", countryDTOs);
		model.addAttribute("tour", tour);
		
		return "tourpage";
	}
}
