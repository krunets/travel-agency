package by.runets.travelagency.controller;

import by.runets.travelagency.dto.SearchTourDTO;
import by.runets.travelagency.dto.TourDTO;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.service.ITourService;
import by.runets.travelagency.util.converter.DateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Duration;
import java.util.List;

@Slf4j
@RestController
public class TourController {
	@Autowired
	private ITourService<Tour, Long> tourService;
	
	@PostMapping(value = "/tour/search", consumes = "application/json")
	public ResponseEntity<?> seacrhTour (@Valid @RequestBody SearchTourDTO searchTourDTO) {
		List<TourDTO> tours = tourService.findTourByCountryAndDateAndDuration(
				searchTourDTO.getCountryName(),
				DateConverter.convert(searchTourDTO.getStartTourDate()),
				Duration.ofDays(new Long(searchTourDTO.getTourDuration()))
		);
		return ResponseEntity.ok(tours);
	}
}
