package by.runets.travelagency.util.config;

import by.runets.travelagency.util.annotation.Developement;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Developement
@Configuration
@AllArgsConstructor
@EnableTransactionManagement
@PropertySource("classpath:properties/development-database-config.properties")
public class DevelopmentHibernateBeanConfig {
	private static final String JDBC_DRIVER_CLASS_NAME = "jdbc.driverClassName";
	private static final String JDBC_URL = "jdbc.url";
	private static final String JDBC_USER_NAME = "jdbc.user_name";
	private static final String JDBC_PASSWORD = "jdbc.password";
	private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	private static final String HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
	private static final String HIBERNATE_HBM2DD1_AUTO = "hibernate.hbm2ddl.auto";
	private static final String HIBERNATE_GENERATE_STATISTICS = "hibernate.generate_statistics";
	private static final String SCAN = "by.runets.travelagency.entity";
	
	private Environment environment;
	
	@Bean
	public HikariDataSource dataSource () {
		HikariDataSource dataSource = new HikariDataSource();
		
		dataSource.setDriverClassName(environment.getProperty(JDBC_DRIVER_CLASS_NAME));
		dataSource.setJdbcUrl(environment.getProperty(JDBC_URL));
		dataSource.setUsername(environment.getProperty(JDBC_USER_NAME));
		dataSource.setPassword(environment.getProperty(JDBC_PASSWORD));
		
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory () {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(dataSource());
		
		Properties properties = new Properties();
		properties.put(HIBERNATE_SHOW_SQL, environment.getProperty(HIBERNATE_SHOW_SQL));
		properties.put(HIBERNATE_DIALECT, environment.getProperty(HIBERNATE_DIALECT));
		properties.put(HIBERNATE_FORMAT_SQL, environment.getProperty(HIBERNATE_FORMAT_SQL));
		properties.put(HIBERNATE_HBM2DD1_AUTO, environment.getProperty(HIBERNATE_HBM2DD1_AUTO));
		properties.put(HIBERNATE_USE_SQL_COMMENTS, environment.getProperty(HIBERNATE_USE_SQL_COMMENTS));
		properties.put(HIBERNATE_GENERATE_STATISTICS, environment.getProperty(HIBERNATE_GENERATE_STATISTICS));
		
		localSessionFactoryBean.setHibernateProperties(properties);
		localSessionFactoryBean.setPackagesToScan(SCAN);
		
		return localSessionFactoryBean;
	}
	
	@Bean
	public HibernateTransactionManager hibernateTransactionManager () {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
		return hibernateTransactionManager;
	}
}
