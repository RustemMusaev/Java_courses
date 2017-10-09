package ru.rustem.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class JdbcConfig{

    @Value("${db.url}")
    private String url;
    @Value("${db.driver}")
    private String driver;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String pwd;
    @Value("${db.maximumPoolSize}")
    private int maxPoolSize;

  @Bean
  public DataSource dataSource() {
      HikariDataSource datasource = new HikariDataSource();
      datasource.setMaximumPoolSize(maxPoolSize);
      datasource.setDriverClassName(driver);
      datasource.setJdbcUrl(url);
      datasource.setUsername(username);
      datasource.setPassword(pwd);
      return datasource;
  }
}
