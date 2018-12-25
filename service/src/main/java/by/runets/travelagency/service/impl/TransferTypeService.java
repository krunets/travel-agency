package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.TransferType;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.service.ITransferTypeService;
import org.springframework.stereotype.Service;

@Service
public class TransferTypeService extends AbstractService<TransferType> implements ITransferTypeService  {
  public TransferTypeService(Class<TransferType> classType, IDatabaseRepository<TransferType, Long> abstractRepository) {
	super(classType, abstractRepository);
  }
}
