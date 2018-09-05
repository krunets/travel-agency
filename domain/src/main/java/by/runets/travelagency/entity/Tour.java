package by.runets.travelagency.entity;

import by.runets.travelagency.converter.DurationConverter;
import by.runets.travelagency.converter.TourTypeConverter;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.runets.travelagency.util.constant.NamedQueryConstant.*;

/**
 * Class that represents the entity of the tour.
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(schema = "travel_agency", name = "tour")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"users", "countries", "hotels", "reviews"})
@EqualsAndHashCode(exclude = {"users", "countries", "hotels"})
@NamedQueries({@NamedQuery(
		name = FIND_TOUR_BY_COUNTRY_AND_DATE_AND_DURATION,
		query = FIND_TOUR_BY_COUNTRY_AND_DATE_AND_DURATION_NAMED_QUERY
),
		@NamedQuery(
				name = FIND_TOUR_ALL_TOUR,
				query = FIND_TOUR_ALL_TOUR_NAMED_QUERY
		),
		@NamedQuery(
				name = FIND_TOUR_BY_ID_WITH_USER_REVIEWS,
				query = FIND_TOUR_BY_ID_WITH_USER_REVIEWS_NAMED_QUERY
		)
})
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
			schema = "travel_agency",
			name = "tour_m2m_user",
			joinColumns = {@JoinColumn(name = "t_id")},
			inverseJoinColumns = {@JoinColumn(name = "u_id")}
	)
	@BatchSize(size = 5)
	private List<User> users = new ArrayList<>();
	/**
	 * This is a field which represents a tour country list.
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			schema = "travel_agency",
			name = "tour_m2m_country",
			joinColumns = {@JoinColumn(name = "t_id")},
			inverseJoinColumns = {@JoinColumn(name = "c_id")}
	)
	@BatchSize(size = 5)
	private List<Country> countries = new ArrayList<>();
	/**
	 * This is a field which represents a list of hotels.
	 */
	@OneToMany(mappedBy = "tour")
	@BatchSize(size = 5)
	private List<Hotel> hotels = new ArrayList<>();
	/**
	 * This is a field which represents a list of reviews.
	 */
	@OneToMany(mappedBy = "tour")
	@BatchSize(size = 5)
	private List<Review> reviews = new ArrayList<>();
}