package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.repository.IDatabaseRepository;
import by.runets.travelagency.repository.query.HotelQuery;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Deprecated
@AllArgsConstructor
@Repository("HotelRepository")
public class HotelRepository implements IDatabaseRepository<Hotel, Integer> {
	@Autowired
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public void create (final Hotel entity) {
		namedParameterJdbcTemplate.update(HotelQuery.INSERT_INTO_HOTEL, new BeanPropertySqlParameterSource(entity));
	}
	
	@Override
	public List<Optional<Hotel>> readAll () {
		try {
			List<Hotel> hotels = namedParameterJdbcTemplate.query(HotelQuery.READ_ALL_HOTEL, new HotelRowMapper());
			return hotels.stream()
					.map(Optional::ofNullable)
					.collect(Collectors.toList());
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	
	@Override
	public Optional<Hotel> read (final Integer id) {
		try {
			Hotel country =
					namedParameterJdbcTemplate.queryForObject(
							HotelQuery.READ_HOTEL_BY_ID, new MapSqlParameterSource("id", id), new HotelRowMapper());
			return Optional.ofNullable(country);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	@Override
	public void update (final Hotel entity) {
		namedParameterJdbcTemplate.update(HotelQuery.UPDATE_HOTEL_BY_ID, new BeanPropertySqlParameterSource(entity));
		
	}
	
	@Override
	public void delete (final Hotel entity) {
		namedParameterJdbcTemplate.update(HotelQuery.DELETE_HOTEL_BY_ID, new BeanPropertySqlParameterSource(entity));
	}
	
	private static final class HotelRowMapper implements RowMapper<Hotel> {
		@Override
		public Hotel mapRow (ResultSet resultSet, int i) throws SQLException {
			Hotel hotel = new Hotel();
			Country country = new Country();
			
			
			hotel.setId(resultSet.getInt("h_id"));
			hotel.setName(resultSet.getString("h_name"));
			hotel.setPhone(resultSet.getString("h_phone"));
			hotel.setStars(resultSet.getInt("h_stars"));
			
			country.setId(resultSet.getInt("c_id"));
			country.setName(resultSet.getString("c_name"));
			
			hotel.setCountry(country);
			return hotel;
		}
	}
}
