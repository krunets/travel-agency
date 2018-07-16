package by.runets.travelagency.util.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@AllArgsConstructor
@EnableTransactionManagement
@ComponentScan(basePackages = "by.runets.travelagency")
public class ServiceBeanConfig {
	
}
