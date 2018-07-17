package by.runets.travelagency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

/**
 * Class that represents the enum of the tour type.
 */
@Entity
@Table(schema = "travel_agency", name = "tour_type")
@Getter
@AllArgsConstructor
public enum TourType {
	ADVENTURE(1, "Adventure tourism"),
	ATOMIC(2, "Atomic tourism"),
	BICYCLE(3, "Bicycle tourism"),
	CULTURAL(4, "Cultural tourism"),
	ECO(5, "Eco tourism"),
	GEO(6, "Geo tourism"),
	INDUSTRIAL(7, "Industrial tourism");
	/**
	 * This is a field which represents a tour type id.
	 */
	@Id
	@Column(name = "t_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private final int id;
	/**
	 * This is a field which represents a tour type.
	 */
	@Column(name = "t_type")
	private final String type;

	public static TourType getTypeByValue(String value) {
		for (TourType tourType : TourType.values()) {
			if (tourType.getType().equals(value)) {
				return tourType;
			}
		}
		return null;
	}
}
