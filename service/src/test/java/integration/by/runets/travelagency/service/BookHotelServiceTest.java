package integration.by.runets.travelagency.service;

import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.entity.Room;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.service.IHotelService;
import by.runets.travelagency.service.IUserService;
import integration.by.runets.travelagency.config.DevelopmentDatabaseBeanConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.runets.travelagency.util.constant.NamedQueryConstant.FIND_USER_WITH_HOTELS;
import static by.runets.travelagency.util.constant.NamedQueryConstant.LOGIN_FIELD;
import static by.runets.travelagency.util.constant.PaginationConstant.DEFAULT_USER_PAGINATION;

@Slf4j
@Transactional
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "development")
@ContextConfiguration(classes = DevelopmentDatabaseBeanConfig.class)
@SqlGroup({
  @Sql(
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
    scripts = {"classpath:db/init-data.sql"}
  )
})
public class BookHotelServiceTest {

  @Autowired private IHotelService hotelService;
  @Autowired private IUserService userService;

  @Test
  public void testBookHotel() {

    final String userName = "admin";
    final String hotelId = "1";
    final int beds = 2;

    final List<User> users =
        userService.readAllByField(
            FIND_USER_WITH_HOTELS, LOGIN_FIELD, userName, 0, DEFAULT_USER_PAGINATION);
    if (users != null && !users.isEmpty()) {
      final User expected = users.get(0);
      if (expected != null) {
        final Hotel hotel = hotelService.read(Long.parseLong(hotelId));
        if (hotel != null) {
          final Room activeRoom = hotelService.findActiveRoom(hotel, beds);

          expected.getBookedRooms().add(activeRoom);
          expected.getHotels().add(hotel);
          userService.update(expected);

          User actual = userService.readAllByField(
              FIND_USER_WITH_HOTELS, LOGIN_FIELD, userName, 0, DEFAULT_USER_PAGINATION).get(0);

          Assert.assertEquals(expected.getBookedRooms(), actual.getBookedRooms());
          Assert.assertEquals(expected.getHotels(), actual.getHotels());
          Assert.assertEquals(expected, actual);
        }
      }
    }
  }
}
