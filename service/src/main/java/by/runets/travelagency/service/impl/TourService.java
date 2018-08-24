package by.runets.travelagency.service.impl;

import by.runets.travelagency.dto.SearchTourDTO;
import by.runets.travelagency.dto.TourDTO;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.hibernate.ITourRepository;
import by.runets.travelagency.service.ITourService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TourService extends AbstractService<Tour, Long> implements ITourService<Tour, Long> {
	@Autowired
	private ITourRepository<Tour, Long> tourRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	public TourService (Class<Tour> classType, IDatabaseRepository<Tour, Long> abstractRepository) {
		super(classType, abstractRepository);
		this.tourRepository = (ITourRepository<Tour, Long>) abstractRepository;
	}
	
	
	@Override
	public List<TourDTO> findTourByCountryAndDateAndDuration (String countryName, LocalDate startTourDate, Duration tourDuration) {
		Type listType = new TypeToken<List<TourDTO>>(){}.getType();
		List<Optional<Tour>> tours = tourRepository.findTourByCountryAndDateAndDuration(countryName, startTourDate, tourDuration);
		return 	modelMapper.map(tours, listType);
	}
}
