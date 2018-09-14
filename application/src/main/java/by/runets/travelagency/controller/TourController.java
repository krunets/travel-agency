package by.runets.travelagency.controller;

import by.runets.travelagency.dto.*;
import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Review;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.service.IReviewService;
import by.runets.travelagency.service.ITourService;
import by.runets.travelagency.util.constant.StringUtils;
import by.runets.travelagency.util.converter.Converter;
import by.runets.travelagency.util.fileuploader.IFileNameExtensionUtil;
import by.runets.travelagency.util.fileuploader.IFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import static by.runets.travelagency.util.constant.NamedQueryConstant.*;
import static by.runets.travelagency.util.constant.PaginationConstant.DEFAULT_PAGINATION_SIZE;

@Slf4j
@Controller
public class TourController {
	@Autowired
	private ITourService<Tour, Long> tourService;
	@Autowired
	private IReviewService<Review, Long> reviewService;
	@Autowired
	private List<CountryDTO> countryDTOs;
	@Autowired
	private IFileUtil fileUtil;
	@Autowired
	private IFileNameExtensionUtil fileNameExtensionUtil;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private Converter<LocalDate, String> dateConverter;
	@Autowired
	private Converter<Tour, TourDTO> tourConverter;
	@Value("${tour.filepath}")
	private String tourFilePath;
	@Value("${tour.database.path}")
	private String tourDatabasePath;
	
	
	@PostMapping(value = "/tour/search")
	public String seacrhTour (@ModelAttribute SearchTourDTO searchTourDTO, Model model) {
		List<Tour> tours = tourService.findTourByCountryAndDateAndDuration(
				searchTourDTO.getCountryName(),
				dateConverter.convert(searchTourDTO.getStartTourDate()),
				Duration.ofDays(searchTourDTO.getTourDuration())
		);
		
		model.addAttribute("checkTours", true);
		model.addAttribute("criteriaTour", tours);
		model.addAttribute("countriesDTO", countryDTOs);
		
		return "home";
	}
	
	@GetMapping(value = "/tour/{tourId}/info")
	public String getTourInfo (@PathVariable String tourId, Model model) {
		List<Tour> tours = tourService.readAllByField(FIND_TOUR_BY_ID_WITH_USER_REVIEWS, ID, Long.parseLong(tourId), 0, DEFAULT_PAGINATION_SIZE);
		if (!tours.isEmpty()) {
			model.addAttribute("countriesDTO", countryDTOs);
			model.addAttribute("tour", tours.get(0));
		}
		return "tourpage";
	}
	
	@PostMapping(value = "/user/review/{tourId}/tour")
	public String review (@ModelAttribute ReviewDTO reviewDTO, @PathVariable String tourId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String username = auth.getName();
		Review review = modelMapper.map(reviewDTO, Review.class);
		reviewService.createReviewByUsernameAndTourId(username, Long.parseLong(tourId), review);
		return "redirect:/tour/{tourId}/info";
	}
	
	@PostMapping(value = "/tour/{tourId}/delete")
	public String deleteTour (@PathVariable String tourId) {
		tourService.delete(new Tour(Long.parseLong(tourId)));
		return "redirect:/";
	}
	
	
	@PostMapping(value = "/tour/add")
	public String addTour (@RequestParam("file") MultipartFile file, @ModelAttribute TourDTO tourDTO) {
		String newFileName = fileNameExtensionUtil.reformat(file);
		fileUtil.save(file, tourFilePath, newFileName);
		tourDTO.setPhoto(tourDatabasePath + File.separator + newFileName);
		Tour tour = tourConverter.convert(tourDTO);
		
		CountryDTO countryDTO = countryDTOs
				.stream()
				.filter(country -> country.getCode().equalsIgnoreCase(tourDTO.getCountryName()))
				.findFirst()
				.orElse(null);
		
		tour.getCountries().add(new Country(countryDTO.getId(), countryDTO.getCode()));
		tourService.create(tour);
		
		return "redirect:/";
	}
	
	@PostMapping(value = "/tour/edit")
	public String editTour (@ModelAttribute TourDTO tourDTO) {
		Tour tour = tourConverter.convert(tourDTO);
		tourService.update(tour);
		return "redirect:/";
	}
}
