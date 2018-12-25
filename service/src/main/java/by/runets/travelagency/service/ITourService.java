package by.runets.travelagency.service;

import by.runets.travelagency.entity.Tour;

import java.math.BigDecimal;
import java.util.List;

public interface ITourService extends IService<Tour, Long> {
  List<Tour> findTourByCountryAndDateAndDuration(
      final String countryName,
      final String startTourDate,
      final List<Long> tourDuration,
      final List<BigDecimal> costs,
      final List<Long> tourTypeIds,
      final int page,
      final int limit);

  List<Tour> findTourByTransferTypeCode(
      final List<Tour> tours, final List<String> transferTypeCodes);
}
