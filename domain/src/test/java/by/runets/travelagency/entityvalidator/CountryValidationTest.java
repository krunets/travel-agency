package by.runets.travelagency.entityvalidator;


import by.runets.travelagency.entity.Country;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


public class CountryTest {
	private static Validator validator;
	
	@Before
	public void setUp () {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void test_country_size () {
		final Country country = new Country();
		
		country.setName("long_name_which_bigger_then_size");
		
		Set<ConstraintViolation<Country>> set = validator.validate(country);
		Assert.assertFalse(set.isEmpty());
	}
	
	@Test
	public void test_country_set_null () {
		final Country country = new Country();
		
		country.setName(null);
		
		Set<ConstraintViolation<Country>> set = validator.validate(country);
		Assert.assertFalse(set.isEmpty());
	}
	
	@Test
	public void test_id_violation() {
		final Country country = new Country();
		
		country.setId(-100);
		
		Set<ConstraintViolation<Country>> set = validator.validate(country);
		Assert.assertFalse(set.isEmpty());
	}
	
	@Test
	public void must_have_no_violations() {
		final Country country = new Country();
		
		country.setId(1);
		country.setName("BY");
		
		Set<ConstraintViolation<Country>> set = validator.validate(country);
		Assert.assertTrue(set.isEmpty());
	}
}
