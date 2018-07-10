package by.runets.travelagency.util.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@AllArgsConstructor
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "by.runets.travelagency")
public class ProductionDatabaseBeanConfig {
	
	@Autowired
	private ProductionDataSourceConfig dataSourceConfig;
	
	public ProductionDataSourceConfig getDataSourceConfig () {
		return dataSourceConfig;
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate () {
		return new NamedParameterJdbcTemplate(dataSourceConfig.hikariProductionDataSource());
	}
}
