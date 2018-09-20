package by.runets.travelagency.service;

import by.runets.travelagency.entity.User;

import java.util.List;

public interface IUserService extends IService<User, Long> {
  boolean registerUserAccount(User user);

  List<User> readUserByRole();
}
