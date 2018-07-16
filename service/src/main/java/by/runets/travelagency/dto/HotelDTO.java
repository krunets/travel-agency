package by.runets.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class HotelDTO {
	private static final String HOTEL_NAME_REGEXP = "([A-Za-z-]{5,20})";
	private static final String INTERNATIONAL_PHONE_REGEXP = "^\\+(?:[0-9] ?){6,14}[0-9]$";
	@NonNull
	private long id;
	@NonNull
	@Pattern(regexp = HOTEL_NAME_REGEXP)
	private String name;
	@NotNull
	@Pattern(regexp = INTERNATIONAL_PHONE_REGEXP)
	private String phone;
	@NotNull
	@Size(max = 5)
	private int stars;
}
