package by.runets.travelagency.converter;

import by.runets.travelagency.config.TestBeanConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestBeanConfig.class)
public class DurationConverterTest {
  @Autowired
  private DurationConverter durationConverter;

  @Test
  public void testConvertToDatabaseColumn() {
	final Duration duration = Duration.ofDays(10);
	final long expected = 10;
	final long actual = durationConverter.convertToDatabaseColumn(duration);

	Assert.assertEquals(actual, expected);
  }

  @Test
  public void testConvertToEntityAttribute() {
	final long id = 10;
	final Duration expected = Duration.ofDays(10);
	final Duration actual = durationConverter.convertToEntityAttribute(id);

	Assert.assertEquals(actual, expected);
  }
}
