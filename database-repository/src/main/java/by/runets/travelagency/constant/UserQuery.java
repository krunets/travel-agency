package by.runets.travelagency.constant;

public class UserQuery {
	public static final String INSERT_INTO_USER = "INSERT INTO travel_agency.\"user\"(u_id, login, password) VALUES (?, ?, ?)";
	public static final String READ_ALL_USER = "SELECT * FROM travel_agency.\"user\"AS u " +
			"JOIN travel_agency.review AS r ON r.\"user\" = u.u_id " +
			"JOIN travel_agency.tour_m2m_user AS tm2mu ON tm2mu.u_id = u.u_id " +
			"JOIN travel_agency.tour AS t ON tm2mu.t_id = t.t_id " +
			"JOIN travel_agency.tour_type AS tt ON tt.t_id = t.t_id ";
	public static final String READ_USER_BY_ID = "SELECT * FROM travel_agency.\"user\" AS u " +
			"LEFT JOIN travel_agency.review AS r ON r.\"user\" = u.u_id " +
			"LEFT JOIN travel_agency.tour_m2m_user AS tm2mu ON tm2mu.u_id = u.u_id " +
			"LEFT JOIN travel_agency.tour AS t ON tm2mu.t_id = t.t_id " +
			"LEFT JOIN travel_agency.tour_type AS tt ON tt.t_id = t.t_id " +
			"WHERE u.u_id = ?";
	public static final String UPDATE_USER_BY_ID = "UPDATE travel_agency.\"user\" SET login=?, password=? WHERE u_id = ?";
	public static final String DELETE_USER_BY_ID = "DELETE FROM travel_agency.\"user\" WHERE u_id = ?";
	public static final String DELETE_USER_CONSTRAINT = "DELETE FROM travel_agency.review WHERE \"user\" = ?";
}
