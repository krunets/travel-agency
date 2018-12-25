package by.runets.travelagency.controller.converter;

import by.runets.travelagency.controller.config.TestConfig;
import by.runets.travelagency.converter.Converter;
import by.runets.travelagency.entity.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Locale;

/*@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)*/
public class CountryCodeToCountryConverterTest {
	@Autowired
  	private Converter<List<Country>, List<Country>> countryCodeToCountryConverter;



	@Test
  	public void testConvert() {
	  Locale locale = new Locale("en_US", "BY");
    System.out.println(locale.getDisplayCountry(locale));
	}
}
