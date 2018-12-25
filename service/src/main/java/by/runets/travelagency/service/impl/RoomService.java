package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Room;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.service.IRoomService;
import org.springframework.stereotype.Service;

@Service
public class RoomService extends AbstractService<Room> implements IRoomService {
  public RoomService(Class<Room> classType, IDatabaseRepository<Room, Long> abstractRepository) {
    super(classType, abstractRepository);
  }
}
