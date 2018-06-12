package by.runets.travelagency.mapper;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.TourType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CountryRowMapper implements RowMapper<Country> {
	@Override
	public Country mapRow (ResultSet resultSet, int i) throws SQLException {
		Country<Integer> country = new Country<>();
		
		Set<Hotel<Integer>> hotelSet = new HashSet<>();
		Set<Tour<Integer>> tourSet = new HashSet<>();
		
		country.setId(resultSet.getInt("c_id"));
		country.setName(resultSet.getString("c_name"));
		
		Hotel<Integer> hotel = new Hotel<>();
		Tour<Integer> tour = new Tour<>();
		
		tour.setId(resultSet.getInt("t_id"));
		tour.setPhoto(resultSet.getString("photo"));
		Date date = resultSet.getDate("date");
		if (date != null) {
			tour.setDate(resultSet.getDate("date").toLocalDate());
		}
		tour.setDescription(resultSet.getString("description"));
		tour.setDuration(Duration.ofDays(resultSet.getLong("duration")));
		tour.setCost(resultSet.getBigDecimal("cost"));
		tour.setTourType(TourType.getTypeByValue(resultSet.getString("t_type")));
		
		tourSet.add(tour);
		
		hotel.setId(resultSet.getInt("h_id"));
		hotel.setName(resultSet.getString("h_name"));
		hotel.setPhone(resultSet.getString("h_phone"));
		hotel.setStars(resultSet.getInt("h_stars"));
		hotel.setCountry(country);
		
		hotelSet.add(hotel);
		
		
		country.setHotels(new ArrayList<>(hotelSet));
		country.setTours(new ArrayList<>(tourSet));
		
		return country;
	}
}
