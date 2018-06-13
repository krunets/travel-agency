package by.runets.travelagency.repository.impl;

import by.runets.travelagency.constant.TourQuery;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.joiner.Joiner;
import by.runets.travelagency.mapper.TourRowMapper;
import by.runets.travelagency.repository.IRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class TourRepository implements IRepository<Tour, Integer> {
	private final JdbcTemplate jdbcTemplate;
	private final Joiner<Tour> joiner;
	
	@Override
	public void create (Tour entity) {
		jdbcTemplate.update(TourQuery.INSERT_INTO_TOUR, entity.getId(), entity.getPhoto(), entity.getDate(), entity.getDescription(), entity.getCost(),
				entity.getTourType().ordinal() + 1, entity.getDuration().toDays());
	}
	
	@Override
	public List<Optional<Tour>> readAll () {
		try {
			List<Tour> tours = jdbcTemplate.query(TourQuery.READ_ALL_TOUR, new TourRowMapper());
			return joiner.join(tours).stream()
					.map(Optional::ofNullable)
					.collect(Collectors.toList());
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	
	@Override
	public Optional<Tour> read (Integer id) {
		try {
			List<Tour> tours = jdbcTemplate.query(TourQuery.READ_TOUR_BY_ID, new Object[]{id}, new TourRowMapper());
			List<Tour> result = joiner.join(tours);
			return  !result.isEmpty()
					? Optional.ofNullable(result.get(0))
					: Optional.empty();
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	@Override
	public void update (Tour entity) {
		jdbcTemplate.update(TourQuery.UPDATE_TOUR_BY_ID, entity.getPhoto(), entity.getDate(),
				entity.getDescription(), entity.getCost(), entity.getTourType().ordinal() + 1,
				entity.getDuration().toDays(), entity.getId());
	}
	
	@Override
	public void delete (Tour entity) {
		jdbcTemplate.update(TourQuery.DELETE_TOUR_M2M_COUNTRY, entity.getId());
		jdbcTemplate.update(TourQuery.DELETE_TOUR_M2M_USER, entity.getId());
		jdbcTemplate.update(TourQuery.DELETE_TOUR_BY_ID, entity.getId());
	}
}
