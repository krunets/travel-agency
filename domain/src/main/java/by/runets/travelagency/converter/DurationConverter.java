package by.runets.travelagency.converter;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;

@Component
@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {
  @Override
  public Long convertToDatabaseColumn(Duration duration) {
	return duration.toDays();
  }

  @Override
  public Duration convertToEntityAttribute(Long attribute) {
	return Duration.ofDays(attribute);
  }
}
