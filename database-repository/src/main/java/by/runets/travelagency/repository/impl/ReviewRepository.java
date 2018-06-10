package by.runets.travelagency.repository.impl;

import by.runets.travelagency.constant.ReviewQuery;
import by.runets.travelagency.entity.Review;
import by.runets.travelagency.mapper.ReviewRowMapper;
import by.runets.travelagency.repository.IRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ReviewRepository implements IRepository<Review, Integer> {

  private final JdbcTemplate jdbcTemplate;

  @Override
  public void create(final Review entity) {
    jdbcTemplate.update(ReviewQuery.INSERT_INTO_REVIEW, entity.getContent(), entity.getUser().getId());
  }

  @Override
  public List<Optional<Review>> readAll() {
    try {
      List<Review> reviews = jdbcTemplate.query(ReviewQuery.READ_ALL_REVIEW, new ReviewRowMapper());
      return reviews.stream()
          .map(Optional::ofNullable)
          .collect(Collectors.toList());
    } catch (EmptyResultDataAccessException e) {
      return Collections.emptyList();
    }
  }

  @Override
  public Optional<Review> read(final Integer id) {
    try {
      Review review =
          jdbcTemplate.queryForObject(
              ReviewQuery.READ_REVIEW_BY_ID, new Object[] {id}, new ReviewRowMapper());
      return Optional.ofNullable(review);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public void update(final Review entity) {
    jdbcTemplate.update(ReviewQuery.UPDATE_REVIEW_BY_ID, entity.getContent(), entity.getUser(), entity.getId());
  }

  @Override
  public void delete(final Review entity) {
    jdbcTemplate.update(ReviewQuery.DELETE_REVIEW_BY_ID, entity.getId());
  }
}
