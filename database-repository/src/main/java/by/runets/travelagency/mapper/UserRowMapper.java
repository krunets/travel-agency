package by.runets.travelagency.mapper;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.TourType;
import by.runets.travelagency.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UserRowMapper implements RowMapper<User> {
	@Override
	public User mapRow (ResultSet resultSet, int i) throws SQLException {
		User<Integer> user = new User<>();
		List<Tour<Integer>> tours = new ArrayList<>();
		List<Review<Integer>> reviews = new ArrayList<>();
		
		user.setId(resultSet.getInt("u_id"));
		user.setLogin(resultSet.getString("login"));
		user.setPassword(resultSet.getString("password"));
		
		Tour<Integer> tour = new Tour<>();
		Review<Integer> review = new Review<>();
		
		tour.setId(resultSet.getInt("t_id"));
		tour.setPhoto(resultSet.getString("photo"));
		Date date = resultSet.getDate("date");
		if (date != null) {
			tour.setDate(resultSet.getDate("date").toLocalDate());
		}		tour.setDescription(resultSet.getString("description"));
		tour.setDuration(Duration.ofDays(resultSet.getLong("duration")));
		tour.setCost(resultSet.getBigDecimal("cost"));
		tour.setTourType(TourType.getTypeByValue(resultSet.getString("t_type")));
		
		tours.add(tour);
		
		review.setId(resultSet.getInt("r_id"));
		review.setContent(resultSet.getString("content"));
		
		reviews.add(review);
		
		user.setTours(tours);
		user.setReviews(reviews);
		
		return user;
	}
}
