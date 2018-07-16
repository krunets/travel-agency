package by.runets.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class UserDTO {
	private static final String LOGIN_REGEXP = "([A-Za-z_\\d]{5,20})";
	private static final String PASS_REGEXP = "^(?=.*?[A-Z])(?=(.*[a-z])+)(?=(.*[\\d]){4,}).{6,20}$";
	
	@NotNull
	private long id;
	@NotNull
	@Pattern(regexp = LOGIN_REGEXP, message = "The login must contain at least 1 uppercase character.")
	private String login;
	
	@NotNull
	@Pattern(
			regexp = PASS_REGEXP,
			message = "The password must contain:" +
					"* At least one upper case English letter" +
					"* At least one lower case English letter" +
					"* At least four digits" +
					"* Minimum length is six characters" +
					"* Maximum length is 20 characters")
	private String password;
}
