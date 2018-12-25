package by.runets.travelagency.service;

import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.entity.Room;

public interface IHotelService extends IService<Hotel, Long> {
  Room findActiveRoom(Hotel hotel, int beds);
}
