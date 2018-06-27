package by.runets.travelagency.util.config;

import by.runets.travelagency.joiner.impl.CountryJoiner;
import by.runets.travelagency.joiner.impl.TourJoiner;
import by.runets.travelagency.joiner.impl.UserJoiner;
import by.runets.travelagency.repository.impl.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Import(ProductionDataSourceConfig.class)
@Configuration
@AllArgsConstructor
@EnableAspectJAutoProxy
public class ProductionDatabaseBeanConfig {
	
	@Autowired
	private ProductionDataSourceConfig dataSourceConfig;
	
	public ProductionDataSourceConfig getDataSourceConfig () {
		return dataSourceConfig;
	}
	
	@Bean
	public CountryJoiner countryJoiner () {
		return new CountryJoiner();
	}
	
	@Bean
	public TourJoiner tourJoiner () {
		return new TourJoiner();
	}
	
	@Bean
	public UserJoiner userJoiner () {
		return new UserJoiner();
	}
	
	@Bean
	public CountryRepository countryRepository () {
		return new CountryRepository(namedParameterJdbcTemplate(), countryJoiner());
	}
	
	@Bean
	public HotelRepository hotelRepository () {
		return new HotelRepository(namedParameterJdbcTemplate());
	}
	
	@Bean
	public ReviewRepository reviewRepository () {
		return new ReviewRepository(namedParameterJdbcTemplate());
	}
	
	@Bean
	public TourRepository tourRepository () {
		return new TourRepository(namedParameterJdbcTemplate(), tourJoiner());
	}
	
	@Bean
	public UserRepository userRepository () {
		return new UserRepository(namedParameterJdbcTemplate(), userJoiner());
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate () {
		return new NamedParameterJdbcTemplate(dataSourceConfig.hikariProductionDataSource());
	}
}
