package by.runets.travelagency.config;

import by.runets.travelagency.config.annotation.Production;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@Production
@PropertySource("classpath:properties/production-database-config.properties")
@AllArgsConstructor
@EnableTransactionManagement
public class ProductionDataSourceConfig {
	
	private static final String URL = "jdbc.url";
	private static final String DRIVER_CLASS_NAME = "jdbc.driverClassName";
	private static final String USER_NAME = "jdbc.username";
	private static final String PASSWORD = "jdbc.password";
	private static final String CONNECTION_TIME_OUT = "jdbc.connectionTimeOut";
	private static final String MAX_LIFE_TIME = "jdbc.maxLifeTime";
	private static final String MAX_POOL_SIZE = "jdbc.maximumPoolSize";
	
	@Autowired
	private Environment environment;
	
	@Bean
	public HikariDataSource hikariProductionDataSource () {
		HikariDataSource dataSource = new HikariDataSource();
		
		dataSource.setJdbcUrl(environment.getProperty(URL));
		dataSource.setDriverClassName(environment.getProperty(DRIVER_CLASS_NAME));
		dataSource.setUsername(environment.getProperty(USER_NAME));
		dataSource.setPassword(environment.getProperty(PASSWORD));
		dataSource.setConnectionTimeout(Long.parseLong(environment.getProperty(CONNECTION_TIME_OUT)));
		dataSource.setMaxLifetime(Long.parseLong(environment.getProperty(MAX_LIFE_TIME)));
		dataSource.setMaximumPoolSize(Integer.parseInt(environment.getProperty(MAX_POOL_SIZE)));
		
		return dataSource;
	}
}