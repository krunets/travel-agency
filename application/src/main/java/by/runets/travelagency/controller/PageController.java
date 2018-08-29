package by.runets.travelagency.controller;

import by.runets.travelagency.dto.CountryDTO;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.service.ITourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

import static by.runets.travelagency.util.config.WebAppConfig.DEFAULT_PAGINATION_SIZE;

@Slf4j
@Controller
public class PageController {
	@Autowired
	private ITourService<Tour, Long> tourService;
	@Autowired
	private List<CountryDTO> countryDTOs;
	
	@GetMapping("/")
	public String start (Locale locale, Model model) {
		List<Tour> tours = tourService.readAll(DEFAULT_PAGINATION_SIZE);
		
		model.addAttribute("checkTours", false);
		model.addAttribute("criteriaTour", "");
		model.addAttribute("tours", tours);
		model.addAttribute("countriesDTO", countryDTOs);
		model.addAttribute("locale", locale);
		
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
	public String adminHomePage () {
		return "admin_homepage";
	}
	
	@GetMapping("/user/home")
	@PreAuthorize("hasRole('MEMBER')")
	public String userHomePage () {
		return "user_homepage";
	}
}
