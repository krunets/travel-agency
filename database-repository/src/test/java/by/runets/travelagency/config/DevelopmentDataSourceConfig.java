package by.runets.travelagency.config;

import by.runets.travelagency.config.annotation.Developement;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


@Configuration
@Developement
@PropertySource("classpath:properties/development-datasource-config.properties")
@AllArgsConstructor
public class DevelopmentDataSourceConfig {
	@Bean
	public HikariDataSource hikariDevelopmentDataSource () {
		HikariDataSource dataSource = new HikariDataSource();
		DataSource ds = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.generateUniqueName(true)
/*				.addScript("db/schema.sql")
				.addScript("db/init-data.sql")*/
				.build();
		dataSource.setDataSource(ds);
		return dataSource;
	}
}
