package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Class that represents the entity of the hotel.
 *
 */
@Data
@Entity
@Table(name = "hotel")
@NoArgsConstructor
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
	@Column(name = "h_name")
	private String name;
	/**
	 * This is a field which represents a hotel phone.
	 */
	@Column(name = "h_phone")
	private String phone;
	/**
	 * This is a field which represents a hotel stars rating.
	 */
	@Column(name = "h_stars")
	private int stars;
	/**
	 * This is a field which represents a country.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_id")
	private Country country;
}
