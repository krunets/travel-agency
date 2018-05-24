package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserRepository extends AbstractRepository<User, Integer> {
	private static List<User> users = new ArrayList<>(Arrays.asList(
			new User<Integer>(1, "root", "root"),
			new User<Integer>(2, "admin", "admin"),
			new User<Integer>(3, "traveler1", "traveler1")));
	
	public UserRepository () {
		super(users);
	}
}