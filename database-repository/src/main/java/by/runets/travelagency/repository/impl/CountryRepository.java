package by.runets.travelagency.repository.impl;

import by.runets.travelagency.constant.CountryQuery;
import by.runets.travelagency.entity.Country;
import by.runets.travelagency.joiner.Joiner;
import by.runets.travelagency.mapper.CountryRowMapper;
import by.runets.travelagency.repository.IRepository;
import lombok.AllArgsConstructor;
import org.omg.CORBA.Object;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CountryRepository implements IRepository<Country, Integer> {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final Joiner<Country> joiner;
	
	@Override
	public void create (final Country entity) {
		Map<String, java.lang.Object> parameters = new HashMap<>();
		parameters.put("c_id", entity.getId());
		parameters.put("c_name", entity.getName());
		namedParameterJdbcTemplate.update(CountryQuery.INSERT_INTO_COUNTRY, parameters);
	}
	
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
	
	@Override
	public Optional<Country> read (final Integer id) {
		try {
			List<Country> countries = namedParameterJdbcTemplate.query(CountryQuery.READ_COUNTRY_BY_ID, new Object[]{id}, new CountryRowMapper());
			List<Country> result = joiner.join(countries);
			return !result.isEmpty()
					? Optional.ofNullable(result.get(0))
					: Optional.empty();
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	@Override
	public void update (final Country entity) {
		Map<String, java.lang.Object> parameters = new HashMap<>();
		parameters.put("c_id", entity.getId());
		parameters.put("c_name", entity.getName());
		namedParameterJdbcTemplate.update(CountryQuery.UPDATE_COUNTRY_BY_ID, parameters);
	}
	
	@Override
	public void delete (final Country entity) {
		Map<String, java.lang.Object> parameters = new HashMap<>();
		parameters.put("c_id", entity.getId());
		parameters.put("c_name", entity.getName());
		namedParameterJdbcTemplate.update(CountryQuery.DELETE_M2M_COUNTRY, parameters);
		namedParameterJdbcTemplate.update(CountryQuery.DELETE_HOTEL_CONSTRAINT, parameters;
		namedParameterJdbcTemplate.update(CountryQuery.DELETE_COUNTRY_BY_ID, parameters);
	}
	
}
