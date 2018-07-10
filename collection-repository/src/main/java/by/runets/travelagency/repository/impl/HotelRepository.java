package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Hotel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Is inherited from common class and provides list of hotels.
 * @deprecated this code will remove in the next version of project, also you can use database-repository
 */
@Deprecated
public class HotelRepository extends AbstractRepository<Hotel, Integer> {
	private static List<Hotel> hotels = new ArrayList<>(Arrays.asList(
			new Hotel<>(1, "Marriot", "123 23 23", 5, new Country<Integer>()),
			new Hotel<>(2, "DoubleTree by Hilton", "232 12 12", 5, new Country<Integer>()),
			new Hotel<>(3, "Prezident-Otel", "111 11 11" , 4, new Country<Integer>()),
			new Hotel<>(4, "Aqua-Minsk", "123 11 11", 2, new Country<Integer>()),
			new Hotel<>(5, "Trump International Hotel Washington DC", "101 10 01", 5, new Country<Integer>())));
	@Deprecated
	public HotelRepository () {
		super(hotels);
	}
}