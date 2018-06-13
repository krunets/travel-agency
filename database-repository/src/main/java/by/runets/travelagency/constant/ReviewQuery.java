package by.runets.travelagency.constant;

public class ReviewQuery {
	private ReviewQuery(){}
	public static final String INSERT_INTO_REVIEW = "INSERT INTO travel_agency.review(r_id, content, \"user\") VALUES(?, ?, ?)";
	public static final String READ_ALL_REVIEW = "SELECT * FROM travel_agency.review AS r LEFT JOIN travel_agency.\"user\" AS u ON u.u_id = r.r_id";
	public static final String READ_REVIEW_BY_ID = "SELECT * FROM travel_agency.review AS r LEFT JOIN travel_agency.\"user\" AS u ON u.u_id = r.r_id WHERE r.r_id=?";
	public static final String UPDATE_REVIEW_BY_ID = "UPDATE travel_agency.review SET content=?, \"user\"=? WHERE r_id=?";
	public static final String DELETE_REVIEW_BY_ID = "DELETE FROM travel_agency.review WHERE r_id=?";
}
