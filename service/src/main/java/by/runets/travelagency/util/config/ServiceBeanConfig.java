package by.runets.travelagency.util.config;

import by.runets.travelagency.service.impl.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Import(ProductionDatabaseBeanConfig.class)
@Configuration
@AllArgsConstructor
@EnableTransactionManagement
public class ServiceBeanConfig implements TransactionManagementConfigurer {
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
	
	@Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager () {
		return new DataSourceTransactionManager(config.getDataSourceConfig().hikariProductionDataSource());
	}
}
