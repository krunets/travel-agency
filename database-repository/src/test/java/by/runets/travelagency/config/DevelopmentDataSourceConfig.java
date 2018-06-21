package by.runets.travelagency.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("development")
@ComponentScan(basePackages = "by.runets.travelagency.*")
@PropertySource("classpath:properties/database-config.properties")
@AllArgsConstructor
public class DevelopmentDataSourceConfig {
}
