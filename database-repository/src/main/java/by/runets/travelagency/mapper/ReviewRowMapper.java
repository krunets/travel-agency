package by.runets.travelagency.mapper;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ReviewRowMapper implements RowMapper<Review> {
	@Override
	public Review mapRow (ResultSet resultSet, int i) throws SQLException {
		Review<Integer> review = new Review<>();
		User<Integer> user = new User<>();
		
		review.setId(resultSet.getInt("r_id"));
		review.setContent(resultSet.getString("content"));
		
		user.setId(resultSet.getInt("u_id"));
		user.setLogin(resultSet.getString("login"));
		user.setPassword(resultSet.getString("password"));
		
		review.setUser(user);
		
		return review;
	}
}
