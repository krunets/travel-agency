package by.runets.travelagency.hibernate.impl.pagination;

import java.util.List;

public interface IPaginationResult<T> {
  List<T> getResultList();
}