package by.runets.travelagency.util.config;

import by.runets.travelagency.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "by.runets.travelagency.*")
public class ServiceBeanConfig {
	@Bean
	public Class<Country> countryClass() {
		return Country.class;
	}
	
	@Bean
	public Class<Hotel> hotelClass() {
		return Hotel.class;
	}
	
	@Bean
	public Class<Review> reviewClass() {
		return Review.class;
	}
	
	@Bean
	public Class<Tour> tourClass() {
		return Tour.class;
	}
	
	@Bean
	public Class<User> userClass() {
		return User.class;
	}
}
