package by.runets.travelagency.hibernate.impl;

import by.runets.travelagency.entity.TransferType;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TransferTypeRepository extends AbstractRepository<TransferType> {
  public TransferTypeRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }
}
