package by.runets.travelagency.controller;

import by.runets.travelagency.dto.SearchTourDTO;
import by.runets.travelagency.dto.TourDTO;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.service.ITourService;
import by.runets.travelagency.util.converter.DateConverter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.List;

@Slf4j
@Controller
public class TourController {
	@Autowired
	private ITourService<Tour, Long> tourService;
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping(value = "/tour/search", consumes = "application/json")
	public String seacrhTour (@ModelAttribute("searchTourDTO") SearchTourDTO searchTourDTO, Model model) {
		Type listType = new TypeToken<List<TourDTO>>() {}.getType();
		List<Tour> tours = tourService.findTourByCountryAndDateAndDuration(
				searchTourDTO.getCountryName(),
				DateConverter.convert(searchTourDTO.getStartTourDate()),
				Duration.ofDays(Long.parseLong(searchTourDTO.getTourDuration()))
		);
		model.addAttribute("checkTours", true);
		model.addAttribute("tours", tours);
		return "index";
	}
}
