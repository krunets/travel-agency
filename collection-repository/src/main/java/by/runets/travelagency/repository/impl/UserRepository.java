package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Is inherited from common class and provides list of users.
 * @deprecated this code will remove in the next version of project, also you can use database-repository
 */
@Deprecated
public class UserRepository extends AbstractRepository<User, Integer> {
	private static List<User> users = new ArrayList<>(Arrays.asList(
			new User<>(1, "root", "root", null, null),
			new User<>(2, "admin", "admin", null, null),
			new User<>(3, "traveler1", "traveler1", null, null)));
	public UserRepository () {
		super(users);
	}
}
