package by.runets.travelagency.repository.impl;

import by.runets.travelagency.constant.TourQuery;
import by.runets.travelagency.entity.Tour;
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
  
  @Override
  public void create(Tour entity) {
    jdbcTemplate.update(TourQuery.INSERT_INTO_TOUR, entity.getPhoto(), entity.getDate(), entity.getDescription(), entity.getCost(),
        entity.getTourType().ordinal(), entity.getDuration());
  }

  @Override
  public List<Optional<Tour>> readAll() {
    try {
      List<Tour> tours = jdbcTemplate.query(TourQuery.READ_ALL_TOUR, new TourRowMapper());
      return tours.stream()
          .map(Optional::ofNullable)
          .collect(Collectors.toList());
    } catch (EmptyResultDataAccessException e) {
      return Collections.emptyList();
    }
  }

  @Override
  public Optional<Tour> read(Integer id) {
    try {
      Tour tour =
          jdbcTemplate.queryForObject(
              TourQuery.READ_TOUR_BY_ID, new Object[] {id}, new TourRowMapper());
      return Optional.ofNullable(tour);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public void update(Tour entity) {
    jdbcTemplate.update(TourQuery.UPDATE_TOUR_BY_ID, entity.getPhoto(), entity.getDate(),
        entity.getDescription(), entity.getCost(), entity.getTourType().ordinal(),
        entity.getDuration(), entity.getId());
  }

  @Override
  public void delete(Tour entity) {
    jdbcTemplate.update(TourQuery.DELETE_TOUR_BY_ID, entity.getId());
  }
}
