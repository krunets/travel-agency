package by.runets.travelagency.repository.impl;

import by.runets.travelagency.constant.CountryQuery;
import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.joiner.Joiner;
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
	private final JdbcTemplate jdbcTemplate;
	private final Joiner<Country> joiner;
	
	@Override
	public void create (final Country entity) {
		jdbcTemplate.update(CountryQuery.INSERT_INTO_COUNTRY, entity.getId(), entity.getName());
	}
	
	@Override
	public List<Optional<Country>> readAll () {
		try {
			List<Country> countries = jdbcTemplate.query(CountryQuery.READ_ALL_COUNTRY, new CountryRowMapper());
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
			List<Country> countries = jdbcTemplate.query(CountryQuery.READ_COUNTRY_BY_ID, new Object[]{id}, new CountryRowMapper());
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
		jdbcTemplate.update(CountryQuery.UPDATE_COUNTRY_BY_ID, entity.getName(), entity.getId());
	}
	
	@Override
	public void delete (final Country entity) {
		jdbcTemplate.update(CountryQuery.DELETE_M2M_COUNTRY, entity.getId());
		jdbcTemplate.update(CountryQuery.DELETE_HOTEL_CONSTRAINT, entity.getId());
		jdbcTemplate.update(CountryQuery.DELETE_COUNTRY_BY_ID, entity.getId());
	}
}
