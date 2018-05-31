package by.runets.travelagency.entity;

/**
 * Class that represents the enum of the tour type.
 */
public enum TourType {
	ADVENTURE("Adventure tourism"),
	ATOMIC("Atomic tourism"),
	BICYCLE("Bicycle tourism"),
	CULTURAL("Cultural tourism"),
	ECO("Eco tourism"),
	GEO("Geo tourism"),
	INDUSTRIAL("Industrial tourism");
	/**
	 * This is a field which represents a tour type.
	 */
	private final String type;
	
	/**
	 * @param type constructor argument which initializes tourtype type field.
	 */
	TourType (final String type) {
		this.type = type;
	}
	
	/**
	 * @return type of tour.
	 */
	public String getType () {
		return type;
	}
}
