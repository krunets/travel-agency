package integration.by.runets.travelagency.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "by.runets.travelagency.*", excludeFilters = {
})
public class DevelopmentDatabaseBeanConfig {
}
