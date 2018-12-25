package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.entity.Room;
import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.service.IHotelService;
import org.springframework.stereotype.Service;

@Service
public class HotelService extends AbstractService<Hotel> implements IHotelService {

  public HotelService(Class<Hotel> classType, IDatabaseRepository<Hotel, Long> abstractRepository) {
    super(classType, abstractRepository);
  }

  @Override
  public Room findActiveRoom(Hotel hotel, int beds) {
    return hotel
        .getRooms()
        .stream()
        .filter(room -> room.getBeds() == beds)
        .findFirst()
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    "Active rooms with " + beds + " beds does not exist"));
  }
}
