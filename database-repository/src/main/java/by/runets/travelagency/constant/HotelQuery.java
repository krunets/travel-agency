package by.runets.travelagency.constant;

public class HotelQuery {
  public static final String INSERT_INTO_HOTEL = "INSERT INTO travel_agency.hotel(h_name, h_phone, h_stars, country) VALUES(?,?,?,?)";
  public static final String READ_ALL_HOTEL = "SELECT * FROM travel_agency.hotel AS h JOIN travel_agency.country AS c ON c.c_id = h.country";
  public static final String READ_HOTEL_BY_ID = "SELECT * FROM travel_agency.hotel AS h JOIN travel_agency.country AS c ON c.c_id = h.country WHERE h.h_id=?";
  public static final String UPDATE_HOTEL_BY_ID = "UPDATE travel_agency.hotel SET h_name=?, h_phone=?, h_stars=?, country=? WHERE h_id=?";
  public static final String DELETE_HOTEL_BY_ID = "DELETE FROM travel_agency.hote WHERE h_id=?";
}
