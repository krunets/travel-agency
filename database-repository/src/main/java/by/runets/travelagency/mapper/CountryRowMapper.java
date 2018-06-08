package by.runets.travelagency.mapper;


import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.TourType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

public class CountryRowMapper implements RowMapper<Country<Integer>> {
	@Override
	public Country<Integer> mapRow (ResultSet resultSet, int i) throws SQLException {
		Country<Integer> country = new Country<Integer>();
		
		country.setId(resultSet.getInt("c_id"));
		country.setName(resultSet.getString("c_name"));
		
		while (resultSet.next()) {
			Hotel<Integer> hotel = new Hotel<>();
			Tour<Integer> tour = new Tour<>();
			
			tour.setId(resultSet.getInt("t_id"));
			tour.setPhoto(resultSet.getString("photo"));
			tour.setDate(resultSet.getDate("date").toLocalDate());
			tour.setDuration(Duration.ofDays(resultSet.getLong("duration")));
			tour.setCost(resultSet.getBigDecimal("cost"));
			tour.setTourType(TourType.getTypeByValue(resultSet.getString("t_type")));
			
			hotel.setId(resultSet.getInt("h_id"));
			hotel.setName(resultSet.getString("h_name"));
			hotel.setPhone(resultSet.getString("h_phone"));
			hotel.setStars(resultSet.getInt("h_stars"));
			hotel.setCountry(country);
			
			country.getHotels().add(hotel);
			
		}
		
		return country;
	}
}
