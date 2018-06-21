package by.runets.travelagency.config;

import by.runets.travelagency.joiner.impl.CountryJoiner;
import by.runets.travelagency.joiner.impl.TourJoiner;
import by.runets.travelagency.joiner.impl.UserJoiner;
import by.runets.travelagency.repository.impl.*;
import com.codahale.metrics.MetricRegistry;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@ComponentScan(basePackages = "by.runets.travelagency.*")
@AllArgsConstructor
public class ProductionDatabaseBeanConfig {
	@Autowired
	private final ProductionDataSourceConfig dataSourceConfig;
	
	@Bean
	public CountryJoiner countryJoiner() {
		return new CountryJoiner();
	}
	
	@Bean
	public TourJoiner tourJoiner() {
		return new TourJoiner();
	}
	
	@Bean
	public UserJoiner userJoiner() {
		return new UserJoiner();
	}
	
	@Bean
	public CountryRepository countryRepository() {
		return new CountryRepository(namedParameterJdbcTemplate(), countryJoiner());
	}
	
	@Bean
	public HotelRepository hotelRepository() {
		return new HotelRepository(namedParameterJdbcTemplate());
	}
	
	@Bean
	public ReviewRepository reviewRepository() {
		return new ReviewRepository(namedParameterJdbcTemplate());
	}
	
	@Bean
	public TourRepository tourRepository() {
		return new TourRepository(namedParameterJdbcTemplate(), tourJoiner());
	}
	
	@Bean
	public UserRepository userRepository() {
		return new UserRepository(namedParameterJdbcTemplate(), userJoiner());
	}
	
	@Bean
	public MetricRegistry metricRegistry() {
		return new MetricRegistry();
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSourceConfig.hikariDataSource());
	}
}
