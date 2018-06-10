package by.runets.travelagency.constant;


public class CountryQuery {
  public static final String READ_ALL_COUNTRY =
      "SELECT * FROM travel_agency.country AS c "
          + "LEFT JOIN travel_agency.hotel AS h ON h.country = c.c_id "
          + "LEFT JOIN travel_agency.tour_m2m_country AS tm2mc ON tm2mc.c_id = c.c_id "
          + "LEFT JOIN travel_agency.tour AS t ON t.t_id = tm2mc.t_id "
          + "LEFT JOIN travel_agency.tour_type AS tt ON tt.t_id = t.t_id";
  public static final String READ_COUNTRY_BY_ID =
      "SELECT * FROM travel_agency.country AS c "
          + "LEFT JOIN travel_agency.hotel AS h ON h.country = c.c_id "
          + "LEFT JOIN travel_agency.tour_m2m_country AS tm2mc ON tm2mc.c_id = c.c_id "
          + "LEFT JOIN travel_agency.tour AS t ON t.t_id = tm2mc.t_id "
          + "LEFT JOIN travel_agency.tour_type AS tt ON tt.t_id = t.t_id "
          + "WHERE c.c_id = ?";
  public static final String INSERT_INTO_COUNTRY =
      "INSERT INTO travel_agency.country(c_name) VALUES(?)";
  public static final String DELETE_M2M_COUNTRY =
      "DELETE FROM travel_agency.tour_m2m_country  WHERE c_id=?";
  public static final String DELETE_COUNTRY_BY_ID =
      "DELETE FROM travel_agency.country WHERE c_id = ?";
  public static final String UPDATE_COUNTRY_BY_ID =
      "UPDATE travel_agency.country SET c_name=? WHERE c_id=?";


}
