package by.runets.travelagency.controller;

import by.runets.travelagency.dto.PaginationDTO;
import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.service.IHotelService;
import by.runets.travelagency.util.constant.NamedQueryConstant;
import by.runets.travelagency.util.constant.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static by.runets.travelagency.util.constant.NamedQueryConstant.COUNT_HOTEL;
import static by.runets.travelagency.util.constant.NamedQueryConstant.COUNT_TOUR;


@Controller
public class HotelController {
  @Autowired
  private IHotelService hotelService;

  @GetMapping("/hotel/all")
  public String hotels(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
					   @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
					   Model model) {
	List<Hotel> hotels = hotelService.readAllByField(NamedQueryConstant.FIND_ALL_HOTEL, StringUtils.EMPTY, StringUtils.EMPTY, page, limit);
	long pageAmount = (long) Math.ceil((double) hotelService.count(COUNT_HOTEL) / limit);

	PaginationDTO<Hotel> paginationDTO = new PaginationDTO<>();
	paginationDTO.setData(hotels);
	paginationDTO.setPage(page);
	paginationDTO.setLimit(limit);
	paginationDTO.setPageAmount(pageAmount);

	model.addAttribute("hotelPaginationDTO", paginationDTO);

	return "hotelpage";
  }

}
