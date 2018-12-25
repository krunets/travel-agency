package by.runets.travelagency.util.constant;

public class NamedQueryConstant {
  private NamedQueryConstant() {}

  // name
  public static final String FIND_BY_ROLE = "FIND_BY_ROLE";
  public static final String FIND_BY_LOGIN = "FIND_BY_LOGIN";
  public static final String FIND_USER_WITH_HOTELS = "FIND_USER_WITH_HOTELS";
  public static final String FIND_TOUR_BY_COUNTRY_AND_DATE_AND_DURATION =
      "FIND_TOUR_BY_COUNTRY_AND_DATE_AND_DURATION";
  public static final String FIND_COUNTRY_BY_NAME = "FIND_COUNTRY_BY_NAME";
  public static final String FIND_ALL_TOUR = "FIND_ALL_TOUR";
  public static final String FIND_ALL_HOTEL = "FIND_ALL_HOTEL";
  public static final String FIND_TOUR_BY_ID_WITH_USER_REVIEWS =
      "FIND_TOUR_BY_ID_WITH_USER_REVIEWS";
  public static final String FIND_HOTELS_BY_TOUR_ID = "FIND_HOTELS_BY_TOUR_ID";
  public static final String FIND_ACTIVE_ROOM = "FIND_ACTIVE_ROOM";
  public static final String FIND_TRANSFER_TYPE_BY_CODE = "FIND_TOURS_BY_TRANSFER_TYPE";

  public static final String COUNT_TOUR = "COUNT_TOUR";
  public static final String COUNT_TOUR_BY_SEARCH_CRITERIA = "COUNT_TOUR_BY_SEARCH_CRITERIA";
  public static final String COUNT_HOTEL = "COUNT_HOTEL";
  public static final String COUNT_REVIEW = "COUNT_REVIEW";
  public static final String COUNT_COUNTRY = "COUNT_COUNTRY";
  public static final String COUNT_USER = "COUNT_USER";

  // query
  public static final String FIND_BY_ROLE_NAMED_QUERY = "from User u WHERE u.role=:role";
  public static final String FIND_BY_LOGIN_NAMED_QUERY = "from User u WHERE u.login=:login";
  public static final String FIND_TOUR_BY_COUNTRY_AND_DATE_AND_DURATION_NAMED_QUERY =
      "FROM Tour t "
          + "JOIN FETCH t.countries country "
          + "WHERE country.name=:countryName "
          + "AND t.date = :date "
          + "AND t.duration between :duration_from and :duration_to";

  public static final String COUNT_TOUR_BY_SEARCH_CRITERIA_NAMED_QUERY =
      "SELECT count(t.id) "
          + "FROM Tour t "
          + "JOIN t.countries "
          + "WHERE country.name=:countryName "
          + "AND t.date = :date "
          + "AND t.duration between :duration_from and :duration_to";

  public static final String FIND_COUNTRY_BY_NAME_NAMED_QUERY =
      "from Country c WHERE c.name=:countryName";
  public static final String FIND_ALL_TOUR_NAMED_QUERY = "from Tour t JOIN FETCH t.countries";
  public static final String FIND_ALL_HOTEL_NAMED_QUERY =
      "from Hotel h WHERE h.latitude IS NOT NULL AND h.longitude IS NOT NULL";
  public static final String FIND_TOUR_BY_ID_WITH_USER_REVIEWS_NAMED_QUERY =
      "from Tour t " + "LEFT JOIN FETCH t.reviews review" + " WHERE t.id=:id";
  public static final String FIND_USER_WITH_HOTELS_NAMED_QUERY =
      "from User u LEFT JOIN FETCH u.hotels WHERE u.login=:login";

  public static final String FIND_HOTELS_BY_TOUR_ID_QUERY = "from Hotel h WHERE h.tour.id=:tour";
  public static final String FIND_ACTIVE_ROOM_NAMED_QUERY =
      "from Room r WHERE r.user.id IS NULL AND r.hotel.id=:hotel";
  public static final String FIND_TRANSFER_TYPE_BY_CODE_QUERY =
      "from TransferType tt WHERE tt.code=:code";

  public static final String COUNT_TOUR_NAMED_QUERY =
      "SELECT count(t.id) FROM Tour t JOIN t.countries";
  public static final String COUNT_HOTEL_NAMED_QUERY = "SELECT count(h.id) FROM Hotel h";
  public static final String COUNT_REVIEW_NAMED_QUERY = "SELECT count(r.id) FROM Review r";
  public static final String COUNT_COUNTRY_NAMED_QUERY = "SELECT count(c.id) FROM Country c";
  public static final String COUNT_USER_NAMED_QUERY = "SELECT count(u.id) FROM User u";

  // field
  public static final String ID = "id";
  public static final String LOGIN_FIELD = "login";
  public static final String ROLE_FIELD = "role";
  public static final String COUNTRY_NAME_FIELD = "countryName";
  public static final String DATE_FIELD = "date";
  public static final String DURATION_FIELD = "duration";

  public static final String DURATION_FROM_FIELD = "duration_from";
  public static final String DURATION_TO_FIELD = "duration_to";

  public static final String TOUR_FIELD = "tour";
  public static final String HOTEL_FIELD = "hotel";
  public static final String TRANSFER_TYPE_CODE_FIELD = "code";
}
