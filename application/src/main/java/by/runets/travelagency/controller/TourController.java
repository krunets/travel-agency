package by.runets.travelagency.controller;

import by.runets.travelagency.dto.CountryDTO;
import by.runets.travelagency.dto.PaginationDTO;
import by.runets.travelagency.dto.SearchTourDTO;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.service.ITourService;
import by.runets.travelagency.util.converter.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
public class TourController {
	@Autowired
	private ITourService<Tour, Long> tourService;
	@Autowired
	private Converter<LocalDate, String> dateConverter;
	@Autowired
	private List<CountryDTO> countryDTOs;
	
	@PostMapping(value = "/tour/search")
	public String seacrhTour (@Valid @ModelAttribute("searchTourDTO") SearchTourDTO searchTourDTO, Model model) {
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
}
