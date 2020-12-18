package edu.tum.ase.project.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("dev")
public class DevelopmentConfiguration {
    @Value("${spring.datasource.url}") private String jdbcUrl;
    @Value("${spring.datasource.username}") private String dbUserName;
    @Value("${spring.datasource.password}") private String dbPassword;
    @Value("${spring.datasource.driverClassName}") private String dbDriverClassName;
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setDriverClassName(dbDriverClassName);
        config.setUsername(dbUserName);
        config.setPassword(dbPassword);
        return new HikariDataSource(config);
    }
}
