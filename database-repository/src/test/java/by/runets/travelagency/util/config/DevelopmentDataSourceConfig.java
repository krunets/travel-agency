package by.runets.travelagency.util.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


@Configuration
@AllArgsConstructor
public class DevelopmentDataSourceConfig {
	@Bean
	public HikariDataSource hikariDevelopmentDataSource () {
		HikariDataSource dataSource = new HikariDataSource();
		DataSource ds = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.generateUniqueName(true)
				.build();
		dataSource.setDataSource(ds);
		return dataSource;
	}
}
