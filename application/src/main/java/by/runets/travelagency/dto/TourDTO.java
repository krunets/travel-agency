package by.runets.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDTO {
	@NotNull
	private String date;
	@NotNull
	private long duration;
	@NotNull
	@Size(min = 10, max = 256)
	private String description;
	@NotNull
	private String cost;
	@NotNull
	private String tourType;
	@NotNull
	private String countryName;
}
