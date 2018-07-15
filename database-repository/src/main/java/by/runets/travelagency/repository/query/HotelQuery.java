package by.runets.travelagency.repository.query;
@Deprecated
public class HotelQuery {
	private HotelQuery(){}
	public static final String INSERT_INTO_HOTEL = "INSERT INTO travel_agency.hotel(h_id, h_name, h_phone, h_stars, country) VALUES(:id, :name, :phone, :stars, :country.id)";
	public static final String READ_ALL_HOTEL = "SELECT h_id, h_name, h_phone, h_stars, c_id, c_name FROM travel_agency.hotel AS h JOIN travel_agency.country AS c ON c.c_id = h.country";
	public static final String READ_HOTEL_BY_ID = "SELECT h_id, h_name, h_phone, h_stars, c_id, c_name FROM travel_agency.hotel AS h JOIN travel_agency.country AS c ON c.c_id = h.country WHERE h.h_id=:id";
	public static final String UPDATE_HOTEL_BY_ID = "UPDATE travel_agency.hotel SET h_name = :name, h_phone = :phone, h_stars = :stars, country = :country.id WHERE h_id=:id";
	public static final String DELETE_HOTEL_BY_ID = "DELETE FROM travel_agency.hotel WHERE h_id=:id";
}
