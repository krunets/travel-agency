package by.runets.travelagency.repository.impl;

import by.runets.travelagency.constant.HotelQuery;
import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.mapper.HotelRowMapper;
import by.runets.travelagency.repository.IRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class HotelRepository implements IRepository<Hotel, Integer> {
	private final JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void create (final Hotel entity) {
		jdbcTemplate.update(HotelQuery.INSERT_INTO_HOTEL, entity.getId(), entity.getName(), entity.getPhone(), entity.getStars(), entity.getCountry().getId());
	}
	
	@Override
	public List<Optional<Hotel>> readAll () {
		try {
			List<Hotel> hotels = jdbcTemplate.query(HotelQuery.READ_ALL_HOTEL, new HotelRowMapper());
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
					jdbcTemplate.queryForObject(
							HotelQuery.READ_HOTEL_BY_ID, new Object[]{id}, new HotelRowMapper());
			return Optional.ofNullable(country);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	@Override
	public void update (final Hotel entity) {
		jdbcTemplate.update(HotelQuery.UPDATE_HOTEL_BY_ID, entity.getName(), entity.getPhone(), entity.getStars(), entity.getCountry().getId(), entity.getId());
		
	}
	
	@Override
	public void delete (final Hotel entity) {
		jdbcTemplate.update(HotelQuery.DELETE_HOTEL_BY_ID, entity.getId());
	}
}
