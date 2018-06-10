package by.runets.travelagency.mapper;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Hotel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class HotelRowMapper implements RowMapper<Hotel> {
  @Override
  public Hotel mapRow(ResultSet resultSet, int i) throws SQLException {
    Hotel<Integer> hotel = new Hotel<>();
    Country<Integer> country = new Country<>();


    hotel.setId(resultSet.getInt("h_id"));
    hotel.setName(resultSet.getString("h_name"));
    hotel.setPhone(resultSet.getString("h_phone"));
    hotel.setStars(resultSet.getInt("h_stars"));

    country.setId(resultSet.getInt("c_id"));
    country.setName(resultSet.getString("c_name"));


	return hotel;
  }
}
