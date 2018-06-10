package by.runets.travelagency.constant;

public class ReviewQuery {
  public static final String INSERT_INTO_REVIEW = "INSERT INTO travel_agency.review(content, user) VALUES(?, ?)";
  public static final String READ_ALL_REVIEW = "";
  public static final String READ_REVIEW_BY_ID = "";
  public static final String UPDATE_REVIEW_BY_ID = "UPDATE travel_agency.review SET content=?, user=? WHERE r_id=?";
  public static final String DELETE_REVIEW_BY_ID = "DELETE FROM travel_agency.review WHERE r_id=?";
}
