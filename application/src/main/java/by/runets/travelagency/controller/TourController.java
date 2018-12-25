package by.runets.travelagency.controller;

import by.runets.travelagency.converter.Converter;
import by.runets.travelagency.dto.CountryDTO;
import by.runets.travelagency.dto.PaginationDTO;
import by.runets.travelagency.dto.SearchTourDTO;
import by.runets.travelagency.dto.TourDTO;
import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.TourType;
import by.runets.travelagency.service.ICountryService;
import by.runets.travelagency.service.IHotelService;
import by.runets.travelagency.service.ITourService;
import by.runets.travelagency.util.fileuploader.FileExtension;
import by.runets.travelagency.util.fileuploader.MultimediaSaver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

import static by.runets.travelagency.util.constant.NamedQueryConstant.*;
import static by.runets.travelagency.util.constant.PaginationConstant.DEFAULT_PAGINATION_SIZE;

@Slf4j
@Controller
public class TourController {
  @Autowired private ITourService tourService;
  @Autowired private IHotelService hotelService;
  @Autowired private ICountryService countryService;
  @Autowired private Converter<Tour, TourDTO> tourConverter;

  @Value("${tour.filepath}")
  private String tourFilePath;

  @Value("${tour.database.path}")
  private String tourDatabasePath;

  @GetMapping(value = "/tour/search")
  public String searchTour(
      @ModelAttribute SearchTourDTO searchTourDTO,
      @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
      @RequestParam(value = "limit", required = false, defaultValue = "5") Integer limit,
      Model model) {
    List<Tour> filteredTours =
        tourService.findTourByTransferTypeCode(
            tourService.findTourByCountryAndDateAndDuration(
                searchTourDTO.getCountryName(),
                searchTourDTO.getStartTourDate(),
                searchTourDTO.getTourDuration(),
                searchTourDTO.getCosts(),
                searchTourDTO.getTourTypeIds(),
                page,
                limit),
            searchTourDTO.getTransferTypeCodes());

    long pageAmount =
        filteredTours.isEmpty()
            ? 0
            : (long) Math.ceil((double) tourService.count(COUNT_TOUR) / limit);

    PaginationDTO<Tour> tourPaginationDTO = new PaginationDTO<>();
    tourPaginationDTO.setData(filteredTours);
    tourPaginationDTO.setPage(page);
    tourPaginationDTO.setLimit(limit);
    tourPaginationDTO.setPageAmount(pageAmount);

    model.addAttribute("tourTypeEnum", TourType.values());
    model.addAttribute("tourPaginationDTO", tourPaginationDTO);

    return "home";
  }

  @GetMapping(value = "/tour/{tourId}/info")
  public String getTourInfo(@PathVariable String tourId, Model model) {
    List<Tour> tours =
        tourService.readAllByField(
            FIND_TOUR_BY_ID_WITH_USER_REVIEWS,
            ID,
            Long.parseLong(tourId),
            0,
            DEFAULT_PAGINATION_SIZE);
    List<Hotel> hotels =
        hotelService.readAllByField(
            FIND_HOTELS_BY_TOUR_ID, TOUR_FIELD, Long.parseLong(tourId), 0, Integer.MAX_VALUE);
    if (!tours.isEmpty()) {
      model.addAttribute("hotels", hotels);
      model.addAttribute("tour", tours.get(0));
    }
    return "tourpage";
  }

  @PostMapping(value = "/tour/{tourId}/delete")
  public String deleteTour(@PathVariable String tourId) {
    tourService.delete(new Tour(Long.parseLong(tourId)));
    return "redirect:/";
  }

  @PostMapping(value = "/tour/add")
  public String addTour(@RequestParam("file") MultipartFile file, @ModelAttribute TourDTO tourDTO) {
    String newFileName = FileExtension.reformat(file);
    MultimediaSaver.save(file, tourFilePath, newFileName);
    tourDTO.setPhoto(tourDatabasePath + File.separator + newFileName);
    Tour tour = tourConverter.convert(tourDTO);
    final List<CountryDTO> countryDTOS = countryService.filterByCriteria(tourDTO.getCountryName());

    if (countryDTOS != null && !countryDTOS.isEmpty()) {
      final CountryDTO countryDTO = countryDTOS.get(0);
      if (countryDTO != null) {
        tour.getCountries().add(new Country(countryDTO.getId(), countryDTO.getData()));
        tourService.create(tour);
      }
    }

    return "redirect:/";
  }

  @PostMapping(value = "/tour/edit")
  public String editTour(@ModelAttribute TourDTO tourDTO) {
    Tour tour = tourConverter.convert(tourDTO);
    tourService.update(tour);
    return "redirect:/";
  }
}
