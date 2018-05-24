package by.runets.travelagency.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tour {
	private int id;
	private String photo;
	private LocalDateTime date;
	private Duration duration;
	private String description;
	private BigDecimal cost;
}
