package by.runets.travelagency.dto;

import by.runets.travelagency.entity.TourType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDTO {
	@NotNull
	private long id;
	@NotNull
	@Size(min = 2, max = 256)
	private String photo;
	@NotNull
	private LocalDate date;
	@NotNull
	private long duration;
	@NotNull
	@Size(min = 10, max = 256)
	private String description;
	@NotNull
	private BigDecimal cost;
	@NotNull
	private TourType tourType;
}
