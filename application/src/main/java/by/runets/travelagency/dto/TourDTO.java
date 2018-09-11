package by.runets.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDTO {
	@NotNull
	@Size(min = 2, max = 256)
	private MultipartFile photo;
	@NotNull
	private String date;
	@NotNull
	private String duration;
	@NotNull
	@Size(min = 10, max = 256)
	private String description;
	@NotNull
	private String cost;
	@NotNull
	private int tourType;
}
