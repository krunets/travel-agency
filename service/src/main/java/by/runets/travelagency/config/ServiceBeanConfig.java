package by.runets.travelagency.config;

import by.runets.travelagency.service.impl.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "by.runets.travelagency.*")
@AllArgsConstructor
public class ServiceBeanConfig {
	@Autowired
	private final ProductionDatabaseBeanConfig config;
	
	@Bean
	public CountryService countryService() {
		return new CountryService(config.countryRepository());
	}
	
	@Bean
	public HotelService hotelService() {
		return new HotelService(config.hotelRepository());
	}
	
	@Bean
	public ReviewService reviewService() {
		return new ReviewService(config.reviewRepository());
	}
	
	@Bean
	public TourService tourService() {
		return new TourService(config.tourRepository());
	}
	
	@Bean
	public UserService userService() {
		return new UserService(config.userRepository());
	}
}
