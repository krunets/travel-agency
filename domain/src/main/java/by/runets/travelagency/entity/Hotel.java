package by.runets.travelagency.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Class that represents the entity of the hotel.
 *
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "hotel")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "country")
@EqualsAndHashCode(exclude = "country")
public class Hotel {
	@Id
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
	@JoinColumn(name = "country")
	private Country country;
}
