package by.runets.travelagency.entityvalidator;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Hotel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class HotelValidationTest {
	private static Validator validator;
	
	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void test_null_violations() {
		final Hotel hotel = new Hotel();
		
		hotel.setId(0);
		hotel.setCountry(null);
		hotel.setName(null);
		hotel.setStars(100);
		hotel.setPhone(null);
		
		Set<ConstraintViolation<Hotel>> set = validator.validate(hotel);
		Assert.assertFalse(set.isEmpty());
	}
	
	@Test
	public void test_no_violations() {
		final Hotel hotel = new Hotel();
	
		
		hotel.setId(5);
		Country country = new Country();
		country.setName("BY");
		hotel.setCountry(country);
		hotel.setName("hotel_name");
		hotel.setStars(100);
		hotel.setPhone("123 23 23");
		
		
		Set<ConstraintViolation<Hotel>> set = validator.validate(hotel);
		Assert.assertTrue(set.isEmpty());
	}
}
