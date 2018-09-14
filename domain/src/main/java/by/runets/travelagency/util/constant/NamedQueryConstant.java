package by.runets.travelagency.util.constant;

public class NamedQueryConstant {
	private NamedQueryConstant () {
	}
	
	//name
	public static final String FIND_BY_ROLE = "FIND_BY_ROLE";
	public static final String FIND_BY_LOGIN = "FIND_BY_LOGIN";
	public static final String FIND_TOUR_BY_COUNTRY_AND_DATE_AND_DURATION = "FIND_TOUR_BY_COUNTRY_AND_DATE_AND_DURATION";
	public static final String FIND_COUNTRY_BY_NAME = "FIND_COUNTRY_BY_NAME";
	public static final String FIND_TOUR_ALL_TOUR = "FIND_TOUR_ALL_TOUR";
	public static final String FIND_ALL_HOTEL = "FIND_ALL_HOTEL";
	public static final String FIND_TOUR_BY_ID_WITH_USER_REVIEWS = "FIND_TOUR_BY_ID_WITH_USER_REVIEWS";
	
	public static final String COUNT_TOUR = "COUNT_TOUR";
	public static final String COUNT_HOTEL = "COUNT_HOTEL";
	public static final String COUNT_REVIEW = "COUNT_REVIEW";
	public static final String COUNT_COUNTRY = "COUNT_COUNTRY";
	public static final String COUNT_USER = "COUNT_USER";
	
	
	//query
	public static final String FIND_BY_ROLE_NAMED_QUERY = "from User u WHERE u.role=:role";
	public static final String FIND_BY_LOGIN_NAMED_QUERY = "from User u WHERE u.login=:login";
	public static final String FIND_TOUR_BY_COUNTRY_AND_DATE_AND_DURATION_NAMED_QUERY = "FROM Tour t " +
			"JOIN FETCH t.countries country " +
			"WHERE country.name=:countryName " +
			"AND t.date = :date " +
			"AND t.duration = :duration";
	public static final String FIND_COUNTRY_BY_NAME_NAMED_QUERY = "from Country c WHERE c.name=:countryName";
	public static final String FIND_TOUR_ALL_TOUR_NAMED_QUERY = "from Tour t JOIN FETCH t.countries";
	public static final String FIND_ALL_HOTEL_NAMED_QUERY = "from Hotel h";
	public static final String FIND_TOUR_BY_ID_WITH_USER_REVIEWS_NAMED_QUERY = "from Tour t " +
			"LEFT JOIN FETCH t.reviews review" +
			" WHERE t.id=:id";
	
	public static final String COUNT_TOUR_NAMED_QUERY = "SELECT count(t.id) FROM Tour t";
	public static final String COUNT_HOTEL_NAMED_QUERY = "SELECT count(h.id) FROM Hotel h";
	public static final String COUNT_REVIEW_NAMED_QUERY = "SELECT count(r.id) FROM Review r";
	public static final String COUNT_COUNTRY_NAMED_QUERY = "SELECT count(c.id) FROM Country c";
	public static final String COUNT_USER_NAMED_QUERY = "SELECT count(u.id) FROM User u";
	
	//field
	public static final String ID = "id";
	public static final String LOGIN_FIELD = "login";
	public static final String ROLE_FIELD = "role";
	public static final String COUNTRY_NAME_FIELD = "countryName";
	public static final String DATE_FIELD = "date";
	public static final String DURATION_FIELD = "duration";
}