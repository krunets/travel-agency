package by.runets.travelagency.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static by.runets.travelagency.util.constant.NamedQueryConstant.*;

/**
 * Class that represents the entity of the hotel.
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(schema = "travel_agency", name = "hotel")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "tour")
@EqualsAndHashCode(exclude = "tour")
@NamedQueries(
		{
				@NamedQuery(name = FIND_ALL_HOTEL, query = FIND_ALL_HOTEL_NAMED_QUERY),
				@NamedQuery(name = COUNT_HOTEL, query = COUNT_HOTEL_NAMED_QUERY)
		}
)
public class Hotel {
	@Id
	@Min(value = 0)
	@Column(name = "h_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	/**
	 * This is a field which represents hotel name.
	 */
	@NotNull
	@Column(name = "h_name")
	private String name;
	/**
	 * This is a field which represents a hotel phone.
	 */
	@NotNull
	@Column(name = "h_phone")
	private String phone;
	/**
	 * This is a field which represents a hotel stars rating.
	 */
	@NotNull
	@Column(name = "h_stars")
	private int stars;
	/**
	 * This is a field which represents a country.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tour")
	private Tour tour;
}
