package by.runets.travelagency.cache;

import java.util.List;

public interface ICache <T> {
  List<T> readAll();
}
