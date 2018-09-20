package by.runets.travelagency.util.config;

import by.runets.travelagency.util.annotation.Production;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.util.Properties;


@Production
@Configuration
@AllArgsConstructor
@EnableTransactionManagement
@PropertySource("classpath:properties/production-database-config.properties")
public class ProductionDataSourceConfig {
  private static final String URL = "jdbc.url";
  private static final String DRIVER_CLASS_NAME = "jdbc.driverClassName";
  private static final String USER_NAME = "jdbc.username";
  private static final String PASS = "jdbc.password";
  private static final String CONNECTION_TIME_OUT = "jdbc.connectionTimeOut";
  private static final String MAX_LIFE_TIME = "jdbc.maxLifeTime";
  private static final String MAX_POOL_SIZE = "jdbc.maximumPoolSize";
  private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
  private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
  private static final String HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
  private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
  private static final String HIBERNATE_GENERATE_STATISTICS = "hibernate.generate_statistics";
  private static final String HIBERNATE_ID_NEW_GENERATOR_MAPPINGS = "hibernate.id.new_generator_mappings";
  private static final String SCAN = "by.runets.travelagency.entity";
  private static final String DIALECT = "hibernate.dialect";

  @Resource
  private Environment environment;

  @Bean
  public HikariDataSource productionDataSource() {
	HikariConfig hikariConfig = new HikariConfig();

	hikariConfig.setJdbcUrl(environment.getProperty(URL));
	hikariConfig.setUsername(environment.getProperty(USER_NAME));
	hikariConfig.setPassword(environment.getProperty(PASS));
	hikariConfig.setDriverClassName(environment.getProperty(DRIVER_CLASS_NAME));
	hikariConfig.setMaxLifetime(Long.parseLong(environment.getProperty(MAX_LIFE_TIME)));
	hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty(MAX_POOL_SIZE)));
	hikariConfig.setConnectionTimeout(Long.parseLong(environment.getProperty(CONNECTION_TIME_OUT)));

	return new HikariDataSource(hikariConfig);
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
	LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
	localSessionFactoryBean.setDataSource(productionDataSource());

	Properties properties = new Properties();
	properties.put(HIBERNATE_SHOW_SQL, environment.getProperty(HIBERNATE_SHOW_SQL));
	properties.put(HIBERNATE_FORMAT_SQL, environment.getProperty(HIBERNATE_FORMAT_SQL));
	properties.put(HIBERNATE_HBM2DDL_AUTO, environment.getProperty(HIBERNATE_HBM2DDL_AUTO));
	properties.put(HIBERNATE_USE_SQL_COMMENTS, environment.getProperty(HIBERNATE_USE_SQL_COMMENTS));
	properties.put(HIBERNATE_GENERATE_STATISTICS, environment.getProperty(HIBERNATE_GENERATE_STATISTICS));
	properties.put(DIALECT, environment.getProperty(DIALECT));
	properties.put(HIBERNATE_ID_NEW_GENERATOR_MAPPINGS, environment.getProperty(HIBERNATE_GENERATE_STATISTICS));

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
