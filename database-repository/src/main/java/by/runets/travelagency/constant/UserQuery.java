package by.runets.travelagency.constant;

public class UserQuery {
  public static final String INSERT_INTO_USER = "INSERT INTO travel_agency.user(login, password) VALUES (?, ?)";
  public static final String READ_ALL_USER = "";
  public static final String READ_USER_BY_ID = "";
  public static final String UPDATE_USER_BY_ID = "UPDATE travel_agency.user SET login=?, password=? WHERE u_id=?";
  public static final String DELETE_USER_BY_ID = "DELETE FROM travel_agency.user WHERE u_id=?";
}
