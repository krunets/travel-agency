package by.runets.travelagency.util.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@AllArgsConstructor
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "by.runets.travelagency")
public class ProductionDatabaseBeanConfig {
}
