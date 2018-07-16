package by.runets.travelagency.converter;

import by.runets.travelagency.entity.TourType;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Component
@Converter(autoApply = true)
public class TourTypeConverter implements AttributeConverter<TourType, Integer> {
	@Override
	public Integer convertToDatabaseColumn (TourType tourType) {
		return tourType.getId();
	}
	
	@Override
	public TourType convertToEntityAttribute (Integer integer) {
		switch (integer) {
			case 1:
				return TourType.ADVENTURE;
			case 2:
				return TourType.ATOMIC;
			case 3:
				return TourType.BICYCLE;
			case 4:
				return TourType.CULTURAL;
			case 5:
				return TourType.ECO;
			case 6:
				return TourType.GEO;
			case 7:
				return TourType.INDUSTRIAL;
		}
		return null;
	}
}
