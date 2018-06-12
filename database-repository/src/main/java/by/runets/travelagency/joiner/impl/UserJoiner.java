package by.runets.travelagency.joiner.impl;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.joiner.Joiner;

import java.util.*;

public class UserJoiner implements Joiner<User> {
	@Override
	public List<User> join (List<User> users) {
		Set<User> result = new LinkedHashSet<>();
		
		for (int i = 0; i < users.size(); i++) {
			Set<Review> reviews = new HashSet<>();
			Set<Tour> tours = new HashSet<>();
			
			reviews.add((Review) users.get(i).getReviews().get(0));
			tours.add((Tour) users.get(i).getTours().get(0));
			for (int j = i + 1; j < users.size(); j++) {
				if (users.get(i).getId() == users.get(i + 1).getId()) {
					reviews.add((Review) users.get(j).getReviews().get(0));
					tours.add((Tour) users.get(j).getTours().get(0));
					users.remove(j);
				}
			}
			users.get(i).setReviews(new ArrayList<>(reviews));
			users.get(i).setTours(new ArrayList<>(tours));
			result.add(users.get(i));
		}
		
		return new ArrayList<>(result);
	}
}
