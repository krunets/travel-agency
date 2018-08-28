package by.runets.travelagency.controller;

import by.runets.travelagency.dto.CountryDTO;
import by.runets.travelagency.dto.SearchTourDTO;
import by.runets.travelagency.dto.TourDTO;
import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.service.IService;
import by.runets.travelagency.service.ITourService;
import by.runets.travelagency.util.converter.Converter;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.Version;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.runets.travelagency.util.config.WebAppConfig.DEFAULT_PAGINATION_SIZE;

@Slf4j
@Controller
public class PageController {
	@Autowired
	private ITourService<Tour, Long> tourService;
	@Autowired
	private List<CountryDTO> countryDTOs;

	@GetMapping("/")
	public String start (Model model) {
		List<Tour> tours = tourService.readAll(DEFAULT_PAGINATION_SIZE);
		
		model.addAttribute("checkTours", false);
		model.addAttribute("criteriaTour", "");
		model.addAttribute("tours", tours);
		model.addAttribute("countriesDTO", countryDTOs);
		
		return "index";
	}
	
	@GetMapping("/login")
	public String loginPage (@RequestParam(value = "error", required = false) String error,
													 @RequestParam(value = "logout", required = false) String logout,
													 Model model) {
		model.addAttribute("error", error != null);
		model.addAttribute("logout", logout != null);
		return "login";
	}
	
	@GetMapping("/homepage")
	public String homePage () {
		return "homepage";
	}
}
