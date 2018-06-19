package by.runets.travelagency.constant;

public class TourQuery {
	private TourQuery(){}
	public static final String INSERT_INTO_TOUR = "INSERT INTO travel_agency.tour(t_id, photo, date, description, cost, tour_type, duration) VALUES(:id, :photo, :date, :description, :cost, :tourType.id, :duration);";
	public static final String READ_ALL_TOUR = "SELECT * FROM travel_agency.tour AS t " +
			"LEFT JOIN travel_agency.tour_m2m_user AS tm2mu ON tm2mu.t_id = t.t_id " +
			"LEFT JOIN travel_agency.\"user\" AS u ON tm2mu.u_id = u.u_id " +
			"LEFT JOIN travel_agency.tour_m2m_country AS tm2mc ON tm2mc.t_id = t.t_id " +
			"LEFT JOIN travel_agency.country AS c ON tm2mc.c_id = c.c_id " +
			"LEFT JOIN travel_agency.tour_type AS tt ON tt.t_id = t.tour_type " +
			"ORDER BY t.t_id";
	public static final String READ_TOUR_BY_ID = "SELECT * FROM travel_agency.tour AS t " +
			"LEFT JOIN travel_agency.tour_m2m_user AS tm2mu ON tm2mu.t_id = t.t_id " +
			"LEFT JOIN travel_agency.\"user\" AS u ON tm2mu.u_id = u.u_id " +
			"LEFT JOIN travel_agency.tour_m2m_country AS tm2mc ON tm2mc.t_id = t.t_id " +
			"LEFT JOIN travel_agency.country AS c ON tm2mc.c_id = c.c_id " +
			"LEFT JOIN travel_agency.tour_type AS tt ON tt.t_id = t.tour_type " +
			"WHERE t.t_id = :id " +
			"ORDER BY t.t_id";
	public static final String UPDATE_TOUR_BY_ID = "UPDATE travel_agency.tour SET photo=:photo, date=:date, description=:description, cost=:cost, tour_type=:tourType.id, duration=:duration WHERE t_id=:id";
	public static final String DELETE_TOUR_BY_ID = "DELETE FROM travel_agency.tour WHERE t_id= :id";
	public static final String DELETE_TOUR_M2M_COUNTRY = "DELETE FROM travel_agency.tour_m2m_country WHERE t_id = :id";
	public static final String DELETE_TOUR_M2M_USER = "DELETE FROM travel_agency.tour_m2m_user WHERE t_id = :id";
}
