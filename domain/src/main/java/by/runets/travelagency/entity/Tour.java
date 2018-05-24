package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Tour <K> extends Entity<K>{
	private String photo;
	private LocalDateTime date;
	private Duration duration;
	private String description;
	private BigDecimal cost;
	
	public Tour (K id, String photo, LocalDateTime date, Duration duration, String description, BigDecimal cost) {
		super(id);
		this.photo = photo;
		this.date = date;
		this.duration = duration;
		this.description = description;
		this.cost = cost;
	}
}
