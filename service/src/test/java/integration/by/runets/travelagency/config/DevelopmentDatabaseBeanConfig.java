package integration.by.runets.travelagency.config;
import by.runets.travelagency.entity.*;
import by.runets.travelagency.util.config.ProductionDataSourceConfig;
import by.runets.travelagency.util.config.ProductionDatabaseBeanConfig;
import org.springframework.context.annotation.*;

@Configuration
@Import(DevelopmentHibernateBeanConfig.class)
@ComponentScan(basePackages = "by.runets.travelagency.*",
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {ProductionDatabaseBeanConfig.class, ProductionDataSourceConfig.class})
)
public class DevelopmentDatabaseBeanConfig {
	public static final int DEFAULT_PAGINATION_SIZE = 10;
	public static final int DEFAULT_PAGE = 1;
	
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
