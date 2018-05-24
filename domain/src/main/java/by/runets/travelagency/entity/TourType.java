package by.runets.travelagency.entity;

public enum TourType {
	ADVENTURE("Adventure tourism"),
	ATOMIC("Atomic tourism"),
	BICYCLE("Bicycle tourism"),
	CULTURAL("Cultural tourism"),
	ECO("Eco tourism"),
	GEO("Geo tourism"),
	INDUSTRIAL("Industrial tourism");
	
	private String type;
	
	TourType (String type) {
		this.type = type;
	}
	
	public String getType () {
		return type;
	}
}
