package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.hibernate.impl.HotelRepository;
import org.springframework.stereotype.Service;

@Service
public class HotelService extends AbstractService<Hotel> {
	public HotelService (HotelRepository hotelRepository, Class<Hotel> classType) {
		super(hotelRepository, classType);
	}
}
