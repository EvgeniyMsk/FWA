package edu.school21.cinema.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
    @Value("${db.url}")
    private String DB_URL;
    @Value("${db.username}")
    private String DB_USERNAME;
    @Value("${db.password}")
    private String DB_PASSWORD;
    @Value("${db.driver.name}")
    private String DB_DRIVER_NAME;

    @Bean
    public DataSource hikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(DB_URL);
        hikariConfig.setDriverClassName(DB_DRIVER_NAME);
        hikariConfig.setUsername(DB_USERNAME);
        hikariConfig.setPassword(DB_PASSWORD);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
