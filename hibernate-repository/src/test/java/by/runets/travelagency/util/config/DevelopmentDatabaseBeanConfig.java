package by.runets.travelagency.util.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@AllArgsConstructor
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "by.runets.travelagency", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ProductionDatabaseBeanConfig.class),
})
public class DevelopmentDatabaseBeanConfig {
}
