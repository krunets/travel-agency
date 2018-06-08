package by.runets.travelagency.constant;

public class CountryQuery {
	//Country
	public final static String READ_ALL_COUNTRY = "SELECT * FROM country AS c " +
			"JOIN hotel AS h ON h.country = c.c_id " +
			"JOIN tour_m2m_country AS tm2mc ON tm2mc.c_id = c.c_id " +
			"JOIN tour AS t ON t.t_id = tm2mc.t_id " +
			"JOIN tour_type AS tt ON tt.t_id = t.t_id";
	public final static String READ_COUNTRY_BY_ID = "SELECT * FROM country AS c " +
			"JOIN hotel AS h ON h.country = c.c_id " +
			"JOIN tour_m2m_country AS tm2mc ON tm2mc.c_id = c.c_id " +
			"JOIN tour AS t ON t.t_id = tm2mc.t_id " +
			"JOIN tour_type AS tt ON tt.t_id = t.t_id " +
			"WHERE c.c_id = ?";
	public final static String READ_TEST = "SELECT * FROM country WHERE c_id=?";
	public final static String INSERT_INTO_COUNTRY = "INSERT INTO country(c_name) VALUES(?)";
	public final static String DELETE_M2M_COUNTRY = "DELETE FROM tour_m2m_country  WHERE c_id = ?";
	public final static String DELETE_COUNTRY_BY_ID = "DELETE FROM country WHERE c_id = ?";
	public final static String UPDATE_COUNTRY_BY_ID = "UPDATE country SET c_name=? WHERE c_id=?";
}
