package integration.by.runets.travelagency.config;

import by.runets.travelagency.util.config.ProductionDatabaseBeanConfig;
import by.runets.travelagency.util.config.ServiceBeanConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@AllArgsConstructor
@Import(DevelopmentDatabaseBeanConfig.class)
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = "by.runets.travelagency", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {ServiceBeanConfig.class, ProductionDatabaseBeanConfig.class})
})
public class IntegrationServiceTestConfig implements TransactionManagementConfigurer {
	@Autowired
	private final DevelopmentDatabaseBeanConfig config;
	
	@Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager () {
		return new DataSourceTransactionManager(config.getDataSourceConfig().hikariDevelopmentDataSource());
	}
}
