package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

/**
 * Class that represents the entity of the tour.
 *
 * @param <K> is a generic param which represents a key param.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Tour<K> extends Entity<K> {
	/**
	 * This is a field which represents a tour photo.
	 */
	private String photo;
	/**
	 * This is a field which represents a tour date.
	 */
	private LocalDate date;
	/**
	 * This is a field which represents a tour duration.
	 */
	private Duration duration;
	/**
	 * This is a field which represents a tour description.
	 */
	private String description;
	/**
	 * This is a field which represents a tour cost.
	 */
	private BigDecimal cost;
	
	/**
	 * Constructor with arguments.
	 *
	 * @param id          constructor argument which initializes tour id field.
	 * @param photo       constructor argument which initializes tour photo field.
	 * @param date        constructor argument which initializes tour date field.
	 * @param duration    constructor argument which initializes tour duration field.
	 * @param description constructor argument which initializes tour description field.
	 * @param cost        constructor argument which initializes tour cost field.
	 */
	public Tour (final K id, final String photo, final LocalDate date,
							 final Duration duration, final String description, final BigDecimal cost) {
		super(id);
		this.photo = photo;
		this.date = date;
		this.duration = duration;
		this.description = description;
		this.cost = cost;
	}
}