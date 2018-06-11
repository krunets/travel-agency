package by.runets.travelagency.constant;

public class TourQuery {
	public static final String INSERT_INTO_TOUR = "INSERT INTO travel_agency.tour(photo, date, description, cost, tour_type, duration)";
	public static final String READ_ALL_TOUR = "SELECT * FROM travel_agency.tour AS t " +
			"JOIN travel_agency.tour_m2m_user AS tm2mu ON tm2mu.t_id = t.t_id " +
			"JOIN travel_agency.user AS u ON tm2mu.u_id = u.u_id " +
			"JOIN travel_agency.tour_m2m_country AS tm2mc ON tm2mc.t_id = t.t_id" +
			"JOIN travel_agency.country AS c ON tm2mc.c_id = c.c_id" +
			"JOIN travel_agency.tour_type AS tt ON tt.t_id = t.t_id";
	public static final String READ_TOUR_BY_ID = "SELECT * FROM travel_agency.tour AS t " +
			"JOIN travel_agency.tour_m2m_user AS tm2mu ON tm2mu.t_id = t.t_id " +
			"JOIN travel_agency.user AS u ON tm2mu.u_id = u.u_id " +
			"JOIN travel_agency.tour_m2m_country AS tm2mc ON tm2mc.t_id = t.t_id" +
			"JOIN travel_agency.country AS c ON tm2mc.c_id = c.c_id" +
			"JOIN travel_agency.tour_type AS tt ON tt.t_id = t.t_id";
	public static final String UPDATE_TOUR_BY_ID = "UPDATE travel_agency.tour SET photo=?, date=?, description=?, cost=?, tour_type=?, duration=? WHERE t_id=?";
	public static final String DELETE_TOUR_BY_ID = "DELETE FROM travel_agency.tour WHERE t_id=?";
}
