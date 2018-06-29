package by.runets.travelagency.util.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@AllArgsConstructor
@EnableTransactionManagement
@ComponentScan(basePackages = "by.runets.travelagency")
public class ServiceBeanConfig implements TransactionManagementConfigurer {
	@Autowired
	private final ProductionDatabaseBeanConfig config;
		
	@Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager () {
		return new DataSourceTransactionManager(config.getDataSourceConfig().hikariProductionDataSource());
	}
}
