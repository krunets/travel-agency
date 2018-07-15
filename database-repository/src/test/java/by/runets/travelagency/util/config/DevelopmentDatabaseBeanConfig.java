package by.runets.travelagency.util.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@AllArgsConstructor
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "by.runets.travelagency", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ProductionDatabaseBeanConfig.class)
})
public class DevelopmentDatabaseBeanConfig {
	@Autowired
	private final DevelopmentDataSourceConfig dataSourceConfig;
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate () {
		return new NamedParameterJdbcTemplate(dataSourceConfig.dataSource());
	}
}
