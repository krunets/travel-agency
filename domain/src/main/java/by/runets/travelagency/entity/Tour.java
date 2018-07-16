package by.runets.travelagency.entity;

import by.runets.travelagency.converter.DurationConverter;
import by.runets.travelagency.converter.TourTypeConverter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the entity of the tour.
 *
 */
@Data
@Entity
@Table(name = "tour")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"users", "countries"})
@EqualsAndHashCode(exclude = {"users", "countries"})
public class Tour {
	@Id
	@Column(name = "t_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	/**
	 * This is a field which represents a tour photo.
	 */
	@NotNull
	private String photo;
	/**
	 * This is a field which represents a tour date.
	 */
	@NotNull
	private LocalDate date;
	/**
	 * This is a field which represents a tour duration.
	 */
	@NotNull
	@Convert(converter = DurationConverter.class)
	private Duration duration;
	/**
	 * This is a field which represents a tour description.
	 */
	@NotNull
	private String description;
	/**
	 * This is a field which represents a tour cost.
	 */
	@NotNull
	@Column(precision = 5)
	private BigDecimal cost;
	/**
	 * This is a field which represents a tour type.
	 */
	@Column(name = "tour_type")
	@Convert(converter = TourTypeConverter.class)
	private TourType tourType;
	/**
	 * This is a field which represents a tour user list.
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "tour_m2m_user",
			joinColumns = {@JoinColumn(name = "t_id")},
			inverseJoinColumns = {@JoinColumn(name = "u_id")}
	)
	private List<User> users = new ArrayList<>();
	/**
	 * This is a field which represents a tour country list.
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "tour_m2m_country",
			joinColumns = {@JoinColumn(name = "t_id")},
			inverseJoinColumns = {@JoinColumn(name = "c_id")}
	)
	private List<Country> countries = new ArrayList<>();
}