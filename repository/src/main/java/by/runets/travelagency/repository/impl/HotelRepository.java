package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Hotel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Is inherited from common class and provides list of hotels.
 */
public class HotelRepository extends AbstractRepository<Hotel, Integer> {
	private static List<Hotel> hotels = new ArrayList<>(Arrays.asList(
			new Hotel<Integer>(1, "Marriot", "123 23 23", 5),
			new Hotel<Integer>(2, "DoubleTree by Hilton", "232 12 12", 5),
			new Hotel<Integer>(3, "Prezident-Otel", "111 11 11" , 4),
			new Hotel<Integer>(4, "Aqua-Minsk", "123 11 11", 2),
			new Hotel<Integer>(5, "Trump International Hotel Washington DC", "101 10 01", 5)));
	
	public HotelRepository () {
		super(hotels);
	}
}