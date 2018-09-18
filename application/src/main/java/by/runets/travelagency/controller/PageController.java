package by.runets.travelagency.controller;

import by.runets.travelagency.dto.CountryDTO;
import by.runets.travelagency.dto.PaginationDTO;
import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.TourType;
import by.runets.travelagency.service.IHotelService;
import by.runets.travelagency.service.IJoinService;
import by.runets.travelagency.service.ITourService;
import by.runets.travelagency.util.constant.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static by.runets.travelagency.util.constant.NamedQueryConstant.*;

@Slf4j
@Controller
public class PageController {
	@Autowired
	private ITourService tourService;
	@Autowired
	private IHotelService hotelService;
	@Autowired
	private IJoinService<Tour, Hotel> joinService;
	@Autowired
	private List<CountryDTO> countryDTOs;
	
	@GetMapping("/")
	public String start (
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
			Model model) {
		List<Tour> tours = tourService.readAllByField(FIND_TOUR_ALL_TOUR, StringUtils.EMPTY, StringUtils.EMPTY, page, limit);
		List<Hotel> hotels = hotelService.readAllByField(FIND_ALL_HOTEL, StringUtils.EMPTY, StringUtils.EMPTY, page, limit);
		joinService.join(tours, hotels);
		
		long pageAmount = (long) Math.ceil((double) tourService.count(COUNT_TOUR) / limit);
		
		PaginationDTO<Tour> tourPaginationDTO = new PaginationDTO<>();
		tourPaginationDTO.setData(tours);
		tourPaginationDTO.setPage(page);
		tourPaginationDTO.setLimit(limit);
		tourPaginationDTO.setPageAmount(pageAmount);
		
		model.addAttribute("checkTours", false);
		model.addAttribute("criteriaTour", "");
		model.addAttribute("countriesDTO", countryDTOs);
		model.addAttribute("tourTypeEnum", TourType.values());
		model.addAttribute("tourPaginationDTO", tourPaginationDTO);
		
		return "home";
	}
	
	
	@GetMapping("/registration")
	public String loadRegistrationPage (@RequestParam(value = "login_error", required = false) String loginError, Model model) {
		model.addAttribute("login_error", loginError != null);
		return "registration";
	}
	
	@GetMapping("/login")
	public String loadLoginPage (@RequestParam(value = "error", required = false) String error,
															 @RequestParam(value = "logout", required = false) String logout,
															 Model model) {
		model.addAttribute("error", error != null);
		model.addAttribute("logout", logout != null);
		return "login";
	}
	
	@GetMapping("/admin/home")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminHomePage (Model model) {
		model.addAttribute("getUsers", false);
		model.addAttribute("users", "");
		return "admin_homepage";
	}
	
	@GetMapping("/user/home")
	@PreAuthorize("hasRole('MEMBER')")
	public String userHomePage () {
		return "user_homepage";
	}
}
