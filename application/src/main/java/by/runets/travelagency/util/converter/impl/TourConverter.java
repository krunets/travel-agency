package by.runets.travelagency.util.converter.impl;

import by.runets.travelagency.dto.TourDTO;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.TourType;
import by.runets.travelagency.util.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

@Component
public class TourConverter implements Converter<Tour, TourDTO> {
  @Autowired
  private Converter<LocalDate, String> dateConverter;

  @Override
  public Tour convert(TourDTO tourDTO) {
	Tour tour = new Tour();

	tour.setId(tourDTO.getId());
	tour.setCost(BigDecimal.valueOf(tourDTO.getCost()));
	tour.setDescription(tourDTO.getDescription());
	tour.setDate(dateConverter.convert(tourDTO.getDate()));
	tour.setDuration(Duration.ofDays(tourDTO.getDuration()));
	tour.setPhoto(tourDTO.getPhoto());
	tour.setTourType(TourType.getTypeByValue(tourDTO.getTourType()));

	return tour;
  }
}
