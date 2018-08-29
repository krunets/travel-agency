package by.runets.travelagency.util.validator;

import by.runets.travelagency.dto.UserDTO;
import by.runets.travelagency.util.annotation.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatches, UserDTO> {
	@Override
	public boolean isValid (UserDTO userDTO, ConstraintValidatorContext constraintValidatorContext) {
		return userDTO.getPassword().equals(userDTO.getConfirmedPassword());
	}
}
