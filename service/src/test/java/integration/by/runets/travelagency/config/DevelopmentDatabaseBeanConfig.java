package integration.by.runets.travelagency.config;

import by.runets.travelagency.util.config.ServiceBeanConfig;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;

@Configuration
@AllArgsConstructor
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "by.runets.travelagency", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {ServiceBeanConfig.class/*, ProductionDatabaseBeanConfig.class, ProductionDataSourceConfig.class*/})
})
public class DevelopmentDatabaseBeanConfig {
}
