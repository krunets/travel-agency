package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.TourType;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.joiner.Joiner;
import by.runets.travelagency.repository.IDatabaseRepository;
import by.runets.travelagency.repository.query.TourQuery;
import by.runets.travelagency.util.annotation.Loggable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
@AllArgsConstructor
public class TourRepository implements IDatabaseRepository<Tour, Integer> {
	@Autowired
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private final Joiner<Tour> joiner;
	
	@Loggable
	@Override
	public void create (Tour entity) {
		namedParameterJdbcTemplate.update(TourQuery.INSERT_INTO_TOUR, NamedQueryFieldProvider.provide(entity));
	}
	
	@Loggable
	@Override
	public List<Optional<Tour>> readAll () {
		try {
			List<Tour> tours = namedParameterJdbcTemplate.query(TourQuery.READ_ALL_TOUR, new TourRowMapper());
			return joiner.join(tours).stream()
					.map(Optional::ofNullable)
					.collect(Collectors.toList());
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	
	@Loggable
	@Override
	public Optional<Tour> read (Integer id) {
		try {
			List<Tour> tours = namedParameterJdbcTemplate.query(TourQuery.READ_TOUR_BY_ID, new MapSqlParameterSource("id", id), new TourRowMapper());
			List<Tour> result = joiner.join(tours);
			return  !result.isEmpty()
					? Optional.ofNullable(result.get(0))
					: Optional.empty();
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	@Loggable
	@Override
	public void update (Tour entity) {
		namedParameterJdbcTemplate.update(TourQuery.UPDATE_TOUR_BY_ID, NamedQueryFieldProvider.provide(entity));
	}
	
	@Loggable
	@Override
	public void delete (Tour entity) {
		namedParameterJdbcTemplate.update(TourQuery.DELETE_TOUR_M2M_COUNTRY, new BeanPropertySqlParameterSource(entity));
		namedParameterJdbcTemplate.update(TourQuery.DELETE_TOUR_M2M_USER, new BeanPropertySqlParameterSource(entity));
		namedParameterJdbcTemplate.update(TourQuery.DELETE_TOUR_BY_ID, new BeanPropertySqlParameterSource(entity));
	}
	
	private static final class TourRowMapper implements RowMapper<Tour> {
		@Override
		public Tour mapRow (ResultSet resultSet, int i) throws SQLException {
			Tour<Integer> tour = new Tour<>();
			List<User<Integer>> users = new ArrayList<>();
			List<Country<Integer>> countries = new ArrayList<>();
			
			tour.setId(resultSet.getInt("t_id"));
			tour.setPhoto(resultSet.getString("photo"));
			Date date = resultSet.getDate("date");
			if (date != null) {
				tour.setDate(date.toLocalDate());
			}				tour.setDescription(resultSet.getString("description"));
			tour.setDuration(Duration.ofDays(resultSet.getLong("duration")));
			tour.setCost(resultSet.getBigDecimal("cost"));
			tour.setTourType(TourType.getTypeByValue(resultSet.getString("t_type")));
			
			
			User<Integer> user = new User<>();
			Country<Integer> country = new Country<>();
			
			user.setId(resultSet.getInt("u_id"));
			user.setLogin(resultSet.getString("login"));
			user.setPassword(resultSet.getString("password"));
			users.add(user);
			
			country.setId(resultSet.getInt("c_id"));
			country.setName(resultSet.getString("c_name"));
			countries.add(country);
			
			tour.setUsers(users);
			tour.setCountries(countries);
			
			
			return tour;
		}
	}

	private static final class NamedQueryFieldProvider {
		private NamedQueryFieldProvider () {}
		
		static Map<String, Object> provide(Tour entity) {
			Map<String, Object> parameters = new HashMap<>();
			
			parameters.put("id", entity.getId());
			parameters.put("photo", entity.getPhoto());
			parameters.put("date", entity.getDate());
			parameters.put("description", entity.getDescription());
			parameters.put("cost", entity.getCost());
			parameters.put("tourType.id", entity.getTourType().getId());
			parameters.put("duration", entity.getDuration().toDays());
			
			return parameters;
		}
	}
}
