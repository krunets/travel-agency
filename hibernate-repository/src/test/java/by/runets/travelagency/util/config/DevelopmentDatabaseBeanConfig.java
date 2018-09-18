package by.runets.travelagency.util.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "by.runets.travelagency", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ProductionDatabaseBeanConfig.class),
})
public class DevelopmentDatabaseBeanConfig {
	public static final int DEFAULT_PAGE = 1;
	public static final int DEFAULT_PAGINATION_SIZE = 10;
}
