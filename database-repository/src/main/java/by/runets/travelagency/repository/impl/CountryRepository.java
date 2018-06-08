package by.runets.travelagency.repository.impl;

import by.runets.travelagency.constant.CountryQuery;
import by.runets.travelagency.entity.Country;
import by.runets.travelagency.mapper.CountryRowMapper;
import by.runets.travelagency.repository.IRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CountryRepository implements IRepository<Country, Integer> {
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void create (Country entity) {
		jdbcTemplate.update(CountryQuery.INSERT_INTO_COUNTRY, entity.getName());
	}
	
	@Override
	public List<Optional<Country>> readAll () {
		try {
			List<Country> countries = jdbcTemplate.query(CountryQuery.READ_ALL_COUNTRY, new CountryRowMapper());
			return countries.stream()
					.map(Optional::ofNullable)
					.collect(Collectors.toList());
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	
	@Override
	public Optional<Country> read (Integer id) {
		try {
			Country country = jdbcTemplate.queryForObject(CountryQuery.READ_TEST, new Object[]{id}, new CountryRowMapper());
			return Optional.ofNullable(country);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	@Override
	public void update (Country entity) {
		jdbcTemplate.update(CountryQuery.UPDATE_COUNTRY_BY_ID, entity.getName(), entity.getId());
	}
	
	@Override
	public void delete (Country entity) {
		jdbcTemplate.update(CountryQuery.DELETE_M2M_COUNTRY, entity.getId());
		jdbcTemplate.update(CountryQuery.DELETE_COUNTRY_BY_ID, entity.getId());
	}
}
