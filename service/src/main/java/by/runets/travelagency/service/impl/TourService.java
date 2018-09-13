package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.hibernate.ITourRepository;
import by.runets.travelagency.service.IJoinService;
import by.runets.travelagency.service.ITourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TourService extends AbstractService<Tour, Long> implements ITourService<Tour, Long>, IJoinService<Tour, Hotel> {
	@Autowired
	private ITourRepository<Tour, Long> tourRepository;
	
	public TourService (Class<Tour> classType, IDatabaseRepository<Tour, Long> abstractRepository) {
		super(classType, abstractRepository);
		this.tourRepository = (ITourRepository<Tour, Long>) abstractRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Tour> findTourByCountryAndDateAndDuration (String countryName, LocalDate startTourDate, Duration tourDuration) {
		return tourRepository
				.findTourByCountryAndDateAndDuration(countryName, startTourDate, tourDuration)
				.stream()
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public void update (Tour updatableEntity) {
		Tour tour = this.read(updatableEntity.getId());
		
		tour.setPhoto(updatableEntity.getPhoto());
		tour.setDescription(updatableEntity.getDescription());
		tour.setCost(updatableEntity.getCost());
		tour.setTourType(updatableEntity.getTourType());
		tour.setDate(updatableEntity.getDate());
		tour.setDuration(updatableEntity.getDuration());
		
		super.update(tour);
	}
	
	@Override
	public void join (List<Tour> tours, List<Hotel> hotels) {
		for (Tour tour : tours) {
			List<Hotel> hotelList = new ArrayList<>();
			for (Hotel hotel : hotels) {
				if (tour.getId() == hotel.getTour().getId()) {
					hotelList.add(hotel);
				}
			}
			tour.setHotels(hotelList);
		}
	}
}
