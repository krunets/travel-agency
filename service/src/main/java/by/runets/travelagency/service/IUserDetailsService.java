package by.runets.travelagency.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserDetailsService {
	UserDetails findUserByLogin (final String login);
}