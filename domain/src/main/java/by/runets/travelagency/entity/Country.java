package by.runets.travelagency.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


/**
 * Class that represents the entity of the country.
 *
 */
@Data
@Entity
@Table(name = "country")
@NoArgsConstructor
@ToString(exclude = {"hotels", "tours"})
@EqualsAndHashCode(exclude = {"hotels", "tours"})
public class Country {
	@Id
	@Column(name = "c_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	/**
	 * This is a field which represents a Country name.
	 */
	@Column(name = "c_name")
	private String name;
	/**
	 * This is a field which represents a list of hotels.
	 */
	@OneToMany(mappedBy = "country")
	private List<Hotel> hotels;
	/**
	 * This is a field which represents a list of tours in exist country.
	 */
	@ManyToMany(mappedBy = "countries")
	private List<Tour> tours;
}
