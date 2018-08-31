package by.runets.travelagency.util.constant;

public class NamedQueryConstant {
	private NamedQueryConstant () {
	}
	
	//name
	public static final String FIND_BY_ROLE = "FIND_BY_ROLE";
	public static final String FIND_BY_LOGIN = "FIND_BY_LOGIN";
	public static final String FIND_TOUR_BY_COUNTRY_AND_DATE_AND_DURATION = "FIND_TOUR_BY_COUNTRY_AND_DATE_AND_DURATION";
	public static final String FIND_COUNTRY_BY_NAME = "FIND_COUNTRY_BY_NAME";
	
	//query
	public static final String FIND_BY_ROLE_NAMED_QUERY = "from User u WHERE u.role=:role";
	public static final String FIND_BY_LOGIN_NAMED_QUERY = "from User u WHERE u.login=:login";
	public static final String FIND_TOUR_BY_COUNTRY_AND_DATE_AND_DURATION_NAMED_QUERY = "FROM Tour t " +
			"JOIN FETCH t.countries country " +
			"WHERE country.name=:countryName " +
			"AND t.date = :date " +
			"AND t.duration = :duration";
	public static final String FIND_COUNTRY_BY_NAME_NAMED_QUERY = "from Country c WHERE c.name=:countryName";
	
	
	//field
	public static final String LOGIN_FIELD = "login";
	public static final String ROLE_FIELD = "role";
	public static final String COUNTRY_NAME_FIELD = "countryName";
	public static final String DATE_FIELD = "date";
	public static final String DURATION_FIELD = "duration";
}
