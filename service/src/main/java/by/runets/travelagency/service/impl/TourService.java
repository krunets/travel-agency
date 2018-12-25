package by.runets.travelagency.service.impl;

import by.runets.travelagency.converter.Converter;
import by.runets.travelagency.dto.CountryDTO;
import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.hibernate.ITourRepository;
import by.runets.travelagency.service.ICountryService;
import by.runets.travelagency.service.IJoinService;
import by.runets.travelagency.service.ITourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TourService extends AbstractService<Tour>
    implements ITourService, IJoinService<Tour, Hotel> {
  @Autowired private ITourRepository tourRepository;
  @Autowired private ICountryService countryService;
  @Autowired private Converter<LocalDate, String> dateConverter;

  public TourService(Class<Tour> classType, IDatabaseRepository<Tour, Long> abstractRepository) {
    super(classType, abstractRepository);
    this.tourRepository = (ITourRepository) abstractRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Tour> findTourByCountryAndDateAndDuration(
      String countryName,
      String startTourDate,
      List<Long> tourDuration,
      List<BigDecimal> costs,
      List<Long> tourTypeIds,
      int page,
      int limit) {

    final List<Duration> durations =
        tourDuration.stream().map(Duration::ofDays).collect(Collectors.toList());
    final CountryDTO countryDTO = countryService.filterByCriteria(countryName).get(0);
    final LocalDate date = dateConverter.convert(startTourDate);
    return tourRepository
        .findTourByCountryAndDateAndDuration(countryDTO.getData(), date, durations, page, limit)
        .stream()
        .filter(Optional::isPresent)
        .map(Optional::get)
        .filter(
            tour ->
                tourTypeIds
                        .stream()
                        .anyMatch(tourTypeId -> tourTypeId == tour.getTourType().getId())
                    && (tour.getCost().longValue() >= costs.get(0).longValue()
                        && tour.getCost().longValue() <= costs.get(costs.size() - 1).longValue()))
        .collect(Collectors.toList());
  }


  @Override
  public List<Tour> findTourByTransferTypeCode(List<Tour> tours, List<String> transferTypeCodes) {
    final List<Tour> toursByCode = new ArrayList<>();
    tours.forEach(
        tour ->
            tour.getTransfers()
                .forEach(
                    transferType ->
                        transferTypeCodes.forEach(
                            transferTypeCode -> {
                              if (transferType.getCode().equals(transferTypeCode)) {
                                toursByCode.add(tour);
                              }
                            })));
    return toursByCode;
  }

  @Override
  @Transactional
  public void update(Tour updatableEntity) {
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
  public void join(List<Tour> tours, List<Hotel> hotels) {
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
