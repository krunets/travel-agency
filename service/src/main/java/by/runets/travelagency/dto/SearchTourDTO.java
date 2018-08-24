package by.runets.travelagency.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Pattern;

@Data
public class SearchTourDTO {
	private static final String REGEX = "[0-9]+";
	
	@NonNull
	private String countryName;
	@NonNull
	private String startTourDate;
	@NonNull
	@Pattern(regexp = REGEX)
	private String tourDuration;
}
