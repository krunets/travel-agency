package by.runets.travelagency.converter;

import by.runets.travelagency.entity.Role;
import by.runets.travelagency.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Component
@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

  @Override
  public String convertToDatabaseColumn(Role role) {
	return role.name();
  }

  @Override
  public Role convertToEntityAttribute(String roleType) {
	Role role = null;
	switch (roleType) {
	  case "ADMIN":
		role = Role.ADMIN;
		break;
	  case "MEMBER":
		role = Role.MEMBER;
		break;
	  default:
		throw new ResourceNotFoundException("The role by name: " + roleType + " does not exist");
	}
	return role;
  }
}
