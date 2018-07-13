package by.runets.travelagency.util.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@AllArgsConstructor
@PropertySource("classpath:properties/production-database-config.properties")
public class DevelopmentHibernateBeanConfig {
	
	private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	private static final String HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
	private static final String HIBERNATE_HBMDD1_AUTO = "hibernate.hbm2ddl.auto";
	private static final String HIBERNATE_GENERATE_STATISTICS = "hibernate.generate_statistics";
	private static final String SCAN = "by.runets.travelagency.entity";
	
	private Environment environment;
	
	@Bean
	public HikariDataSource dataSource () {
		HikariDataSource dataSource = new HikariDataSource();
		DataSource ds = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.generateUniqueName(true)
				.build();
		dataSource.setDataSource(ds);
		return dataSource;
	}
	
	
	@Bean
	public LocalSessionFactoryBean sessionFactory () {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(dataSource());
		
		Properties properties = new Properties();
		properties.put(HIBERNATE_SHOW_SQL, environment.getProperty(HIBERNATE_SHOW_SQL));
		properties.put(HIBERNATE_FORMAT_SQL, environment.getProperty(HIBERNATE_FORMAT_SQL));
/*
		properties.put(HIBERNATE_HBMDD1_AUTO, environment.getProperty(HIBERNATE_HBMDD1_AUTO));
*/
		properties.put(HIBERNATE_USE_SQL_COMMENTS, environment.getProperty(HIBERNATE_USE_SQL_COMMENTS));
		properties.put(HIBERNATE_GENERATE_STATISTICS, environment.getProperty(HIBERNATE_GENERATE_STATISTICS));
		
		localSessionFactoryBean.setHibernateProperties(properties);
		localSessionFactoryBean.setPackagesToScan(SCAN);
		
		return localSessionFactoryBean;
	}
	
	@Bean
	public HibernateTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
		return hibernateTransactionManager;
	}
}
