package by.runets.travelagency.controller;

import by.runets.travelagency.dto.PaginationDTO;
import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.entity.Room;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.service.IHotelService;
import by.runets.travelagency.service.IUserService;
import by.runets.travelagency.util.constant.NamedQueryConstant;
import by.runets.travelagency.util.constant.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static by.runets.travelagency.util.constant.NamedQueryConstant.*;
import static by.runets.travelagency.util.constant.PaginationConstant.DEFAULT_USER_PAGINATION;

@Log4j2
@Controller
public class HotelController {
  @Autowired private IHotelService hotelService;
  @Autowired private IUserService userService;

  @GetMapping("/hotel/all")
  public String hotels(
      @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
      @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
      Model model) {
    List<Hotel> hotels =
        hotelService.readAllByField(
            NamedQueryConstant.FIND_ALL_HOTEL, StringUtils.EMPTY, StringUtils.EMPTY, page, limit);
    long pageAmount = (long) Math.ceil((double) hotelService.count(COUNT_HOTEL) / limit);

    PaginationDTO<Hotel> paginationDTO = new PaginationDTO<>();
    paginationDTO.setData(hotels);
    paginationDTO.setPage(page);
    paginationDTO.setLimit(limit);
    paginationDTO.setPageAmount(pageAmount);

    model.addAttribute("hotelPaginationDTO", paginationDTO);

    return "hotelpage";
  }

  @ResponseBody
  @PostMapping(value = "/hotel/{hotelId}/book/beds/{beds}")
  public ResponseEntity<?> bookHotel(@PathVariable String hotelId, @PathVariable String beds) {
    final String username = SecurityContextHolder.getContext().getAuthentication().getName();
    if (username != null && !username.isEmpty()) {
      final List<User> users =
          userService.readAllByField(
              FIND_USER_WITH_HOTELS, LOGIN_FIELD, username, 0, DEFAULT_USER_PAGINATION);
      if (users != null && !users.isEmpty()) {
        final User user = users.get(0);
        if (user != null) {
          final Hotel hotel = hotelService.read(Long.parseLong(hotelId));
          if (hotel != null) {
            final Room activeRoom = hotelService.findActiveRoom(hotel, Integer.parseInt(beds));

            user.getBookedRooms().add(activeRoom);
            user.getHotels().add(hotel);
            userService.update(user);

            return new ResponseEntity<>("Hotel successfully booked", HttpStatus.OK);
          }
        }
      }
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
}
