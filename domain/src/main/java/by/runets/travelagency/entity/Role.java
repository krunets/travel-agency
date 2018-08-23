package by.runets.travelagency.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
	ADMIN,
	MEMBER;
	
	@Override
	public String getAuthority () {
		return this.name();
	}
}
