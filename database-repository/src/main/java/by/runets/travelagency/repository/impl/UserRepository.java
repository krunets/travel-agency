package by.runets.travelagency.repository.impl;

import by.runets.travelagency.constant.UserQuery;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.joiner.Joiner;
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
	private final Joiner<User> joiner;
	
	@Override
	public void create (User entity) {
		jdbcTemplate.update(UserQuery.INSERT_INTO_USER, entity.getId(), entity.getLogin(), entity.getPassword());
	}
	
	@Override
	public List<Optional<User>> readAll () {
		try {
			List<User> users = jdbcTemplate.query(UserQuery.READ_ALL_USER, new UserRowMapper());
			return joiner.join(users).stream()
					.map(Optional::ofNullable)
					.collect(Collectors.toList());
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	
	@Override
	public Optional<User> read (Integer id) {
		try {
			List<User> users = jdbcTemplate.query(UserQuery.READ_USER_BY_ID, new Object[]{id}, new UserRowMapper());
			List<User> result = joiner.join(users);
			return !result.isEmpty()
					? Optional.ofNullable(result.get(0))
					: Optional.empty();
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	@Override
	public void update (User entity) {
		jdbcTemplate.update(UserQuery.UPDATE_USER_BY_ID, entity.getLogin(), entity.getPassword(), entity.getId());
	}
	
	@Override
	public void delete (User entity) {
		jdbcTemplate.update(UserQuery.DELETE_USER_CONSTRAINT, entity.getId());
		jdbcTemplate.update(UserQuery.DELETE_USER_BY_ID, entity.getId());
	}
}
