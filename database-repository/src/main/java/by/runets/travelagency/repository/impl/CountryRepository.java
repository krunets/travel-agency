package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.TourType;
import by.runets.travelagency.joiner.Joiner;
import by.runets.travelagency.repository.IDatabaseRepository;
import by.runets.travelagency.repository.query.CountryQuery;
import by.runets.travelagency.util.annotation.Loggable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
@Repository
@AllArgsConstructor
public class CountryRepository implements IDatabaseRepository<Country, Integer> {
	@Autowired
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private final Joiner<Country> joiner;
	
	@Loggable
	@Override
	public void create (final Country entity) {
		namedParameterJdbcTemplate.update(CountryQuery.INSERT_INTO_COUNTRY, new BeanPropertySqlParameterSource(entity));
	}
	
	@Loggable
	@Override
	public List<Optional<Country>> readAll () {
		try {
			List<Country> countries = namedParameterJdbcTemplate.query(CountryQuery.READ_ALL_COUNTRY, new CountryRowMapper());
			return joiner.join(countries).stream()
					.map(Optional::ofNullable)
					.collect(Collectors.toList());
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	
	@Loggable
	@Override
	public Optional<Country> read (final Integer id) {
		try {
			List<Country> countries = namedParameterJdbcTemplate.query(CountryQuery.READ_COUNTRY_BY_ID, new MapSqlParameterSource("id", id), new CountryRowMapper());
			List<Country> result = joiner.join(countries);
			return !result.isEmpty()
					? Optional.ofNullable(result.get(0))
					: Optional.empty();
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	@Loggable
	@Override
	public void update (final Country entity) {
		namedParameterJdbcTemplate.update(CountryQuery.UPDATE_COUNTRY_BY_ID, new BeanPropertySqlParameterSource(entity));
	}
	
	@Loggable
	@Override
	public void delete (final Country entity) {
		namedParameterJdbcTemplate.update(CountryQuery.DELETE_M2M_COUNTRY, new BeanPropertySqlParameterSource(entity));
		namedParameterJdbcTemplate.update(CountryQuery.DELETE_HOTEL_CONSTRAINT, new BeanPropertySqlParameterSource(entity));
		namedParameterJdbcTemplate.update(CountryQuery.DELETE_COUNTRY_BY_ID, new BeanPropertySqlParameterSource(entity));
	}
	
	private static final class CountryRowMapper implements RowMapper<Country> {
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
			java.sql.Date date = resultSet.getDate("date");
			if (date != null) {
				tour.setDate(date.toLocalDate());
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
}
