package integration.by.runets.travelagency.config;

import by.runets.travelagency.util.LoggingAspect;
import by.runets.travelagency.util.config.ProductionDatabaseBeanConfig;
import by.runets.travelagency.util.config.ServiceBeanConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@AllArgsConstructor
@Import({DevelopmentDataSourceConfig.class, LoggingAspect.class})
@ComponentScan(basePackages = "by.runets.travelagency", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {ServiceBeanConfig.class, ProductionDatabaseBeanConfig.class})
})
public class DevelopmentDatabaseBeanConfig {
	@Autowired
	private final DevelopmentDataSourceConfig dataSourceConfig;
	
	public DevelopmentDataSourceConfig getDataSourceConfig () {
		return dataSourceConfig;
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSourceConfig.hikariDevelopmentDataSource());
	}
}
