package by.runets.travelagency.mapper;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.TourType;
import by.runets.travelagency.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class TourRowMapper implements RowMapper<Tour> {
  @Override
  public Tour mapRow(ResultSet resultSet, int i) throws SQLException {
    Tour<Integer> tour = new Tour<>();
    List<User<Integer>> users = new ArrayList<>();
    List<Country<Integer>> countries = new ArrayList<>();

    tour.setId(resultSet.getInt("t_id"));
    tour.setPhoto(resultSet.getString("photo"));
    tour.setDate(resultSet.getDate("date").toLocalDate());
    tour.setDescription(resultSet.getString("description"));
    tour.setDuration(Duration.ofDays(resultSet.getLong("duration")));
    tour.setCost(resultSet.getBigDecimal("cost"));
    tour.setTourType(TourType.getTypeByValue(resultSet.getString("t_type")));

    while (resultSet.next()) {
      User<Integer> user = new User<>();
      Country<Integer> country = new Country<>();

      user.setId(resultSet.getInt("u_id"));
      user.setLogin(resultSet.getString("login"));
      user.setPassword(resultSet.getString("password"));
      users.add(user);

      country.setId(resultSet.getInt("c_id"));
      country.setName(resultSet.getString("c_name"));
      countries.add(country);
    }

    tour.setUsers(users);
    tour.setCountries(countries);

	return tour;
  }
}
