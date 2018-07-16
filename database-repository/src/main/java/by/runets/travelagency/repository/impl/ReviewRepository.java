package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.repository.IDatabaseRepository;
import by.runets.travelagency.repository.query.ReviewQuery;
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
@Repository("ReviewRepository")
public class ReviewRepository implements IDatabaseRepository<Review, Integer> {
  @Autowired
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  
  @Override
  public void create(final Review entity) {
    namedParameterJdbcTemplate.update(ReviewQuery.INSERT_INTO_REVIEW, new BeanPropertySqlParameterSource(entity));
  }
  
  @Override
  public List<Optional<Review>> readAll() {
    try {
      List<Review> reviews = namedParameterJdbcTemplate.query(ReviewQuery.READ_ALL_REVIEW, new ReviewRowMapper());
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
          namedParameterJdbcTemplate.queryForObject(
              ReviewQuery.READ_REVIEW_BY_ID, new MapSqlParameterSource("id", id), new ReviewRowMapper());
      return Optional.ofNullable(review);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }
  
  @Override
  public void update(final Review entity) {
    namedParameterJdbcTemplate.update(ReviewQuery.UPDATE_REVIEW_BY_ID, new BeanPropertySqlParameterSource(entity));
  }
  
  @Override
  public void delete(final Review entity) {
    namedParameterJdbcTemplate.update(ReviewQuery.DELETE_REVIEW_BY_ID, new BeanPropertySqlParameterSource(entity));
  }
  
  private static final class ReviewRowMapper implements RowMapper<Review> {
    @Override
    public Review mapRow (ResultSet resultSet, int i) throws SQLException {
      Review review = new Review();
      User user = new User();
  
      review.setId(resultSet.getInt("r_id"));
      review.setContent(resultSet.getString("content"));
  
      user.setId(resultSet.getInt("u_id"));
      user.setLogin(resultSet.getString("login"));
      user.setPassword(resultSet.getString("password"));
  
      review.setUser(user);
  
      return review;
    }
  }
}
