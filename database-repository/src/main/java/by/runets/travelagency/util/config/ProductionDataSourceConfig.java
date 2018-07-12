package by.runets.travelagency.util.config;

import by.runets.travelagency.util.annotation.Production;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.Properties;


@Production
@Configuration
@AllArgsConstructor
@PropertySource("classpath:properties/production-database-config.properties")
public class ProductionDataSourceConfig {
	private static final String URL = "jdbc.url";
	private static final String DRIVER_CLASS_NAME = "jdbc.driverClassName";
	private static final String USER_NAME = "jdbc.username";
	private static final String PASS ="jdbc.password";
	private static final String CONNECTION_TIME_OUT = "jdbc.connectionTimeOut";
	private static final String MAX_LIFE_TIME = "jdbc.maxLifeTime";
	private static final String MAX_POOL_SIZE = "jdbc.maximumPoolSize";
	private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	private static final String HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
	private static final String HIBERNATE_HBMDD1_AUTO = "hibernate.hbm2ddl.auto";
	private static final String SCAN = "by.runets.travelagency.entity";
	
	@Autowired
	private Environment environment;
	
	@Bean
	public HikariDataSource hikariProductionDataSource () {
		try (HikariDataSource dataSource = new HikariDataSource()) {
			dataSource.setJdbcUrl(environment.getProperty(URL));
			dataSource.setDriverClassName(environment.getProperty(DRIVER_CLASS_NAME));
			dataSource.setUsername(environment.getProperty(USER_NAME));
			dataSource.setPassword(environment.getProperty(PASS));
			dataSource.setConnectionTimeout(Long.parseLong(environment.getProperty(CONNECTION_TIME_OUT)));
			dataSource.setMaxLifetime(Long.parseLong(environment.getProperty(MAX_LIFE_TIME)));
			dataSource.setMaximumPoolSize(Integer.parseInt(environment.getProperty(MAX_POOL_SIZE)));
			return dataSource;
		}
	}
	
	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean() {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(hikariProductionDataSource());
		
		Properties properties = new Properties();
		
		properties.put(HIBERNATE_SHOW_SQL, environment.getProperty(HIBERNATE_SHOW_SQL));
		properties.put(HIBERNATE_FORMAT_SQL, environment.getProperty(HIBERNATE_FORMAT_SQL));
		properties.put(HIBERNATE_USE_SQL_COMMENTS, environment.getProperty(HIBERNATE_USE_SQL_COMMENTS));
		properties.put(HIBERNATE_HBMDD1_AUTO, environment.getProperty(HIBERNATE_HBMDD1_AUTO));
		
		localSessionFactoryBean.setHibernateProperties(properties);
		localSessionFactoryBean.setPackagesToScan(SCAN);
		
		return localSessionFactoryBean;
	}
	
}
