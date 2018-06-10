package by.runets.travelagency.constant;

public class TourQuery {
  public static final String INSERT_INTO_TOUR = "INSERT INTO travel_agency.tour(photo, date, description, cost, tour_type, duration)";
  public static final String READ_ALL_TOUR = "";
  public static final String READ_TOUR_BY_ID = "";
  public static final String UPDATE_TOUR_BY_ID = "UPDATE travel_agency.tour SET photo=?, date=?, description=?, cost=?, tour_type=?, duration=? WHERE t_id=?";
  public static final String DELETE_TOUR_BY_ID = "DELETE FROM travel_agency.tour WHERE t_id=?";
}
