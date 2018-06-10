package by.runets.travelagency.repository.impl;

import by.runets.travelagency.constant.UserQuery;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.mapper.UserRowMapper;
import by.runets.travelagency.repository.IRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
public class UserRepository implements IRepository<User, Integer> {
  private final JdbcTemplate jdbcTemplate;

  @Override
  public void create(User entity) {
    jdbcTemplate.update(UserQuery.INSERT_INTO_USER, entity.getLogin(), entity.getPassword());

  }

  @Override
  public List<Optional<User>> readAll() {
    try {
      List<User> users = jdbcTemplate.query(UserQuery.READ_ALL_USER, new UserRowMapper());
      return users.stream()
          .map(Optional::ofNullable)
          .collect(Collectors.toList());
    } catch (EmptyResultDataAccessException e) {
      return Collections.emptyList();
    }
  }

  @Override
  public Optional<User> read(Integer id) {
    try {
      User user =
          jdbcTemplate.queryForObject(
              UserQuery.READ_USER_BY_ID, new Object[] {id}, new UserRowMapper());
      return Optional.ofNullable(user);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public void update(User entity) {
    jdbcTemplate.update(UserQuery.UPDATE_USER_BY_ID, entity.getLogin(), entity.getPassword(), entity.getId());
  }

  @Override
  public void delete(User entity) {
    jdbcTemplate.update(UserQuery.DELETE_USER_BY_ID, entity.getId());
  }
}
