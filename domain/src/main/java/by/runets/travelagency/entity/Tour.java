package by.runets.travelagency.entity;

import by.runets.travelagency.converter.DurationConverter;
import lombok.*;

import javax.persistence.*;
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
	private String photo;
	/**
	 * This is a field which represents a tour date.
	 */
	private LocalDate date;
	/**
	 * This is a field which represents a tour duration.
	 */
	@Convert(converter = DurationConverter.class)
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
	 * This is a field which represents a tour type.
	 */
/*	@Column(name = "tour_type")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_id")*/
	@Enumerated
	@Column(name = "tour_type")
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