package by.runets.travelagency.repository.query;

public class ReviewQuery {
	private ReviewQuery(){}
	public static final String INSERT_INTO_REVIEW = "INSERT INTO travel_agency.review(r_id, content, \"user\") VALUES(:id, :content, :user.id)";
	public static final String READ_ALL_REVIEW = "SELECT r_id, content, u_id, login, password FROM travel_agency.review AS r LEFT JOIN travel_agency.\"user\" AS u ON u.u_id = r.r_id";
	public static final String READ_REVIEW_BY_ID = "SELECT r_id, content, u_id, login, password FROM travel_agency.review AS r LEFT JOIN travel_agency.\"user\" AS u ON u.u_id = r.r_id WHERE r.r_id=:id";
	public static final String UPDATE_REVIEW_BY_ID = "UPDATE travel_agency.review SET content=:content, \"user\"=:user.id WHERE r_id=:id";
	public static final String DELETE_REVIEW_BY_ID = "DELETE FROM travel_agency.review WHERE r_id=:id";
}
