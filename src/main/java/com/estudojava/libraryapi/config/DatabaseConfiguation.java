package com.estudojava.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguation {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    //@Bean
    public DataSource dataSource() {

        // Data source simples, pode quebrar com vários usuários e causar problemas
        DriverManagerDataSource dataSource = new DriverManagerDataSource();


        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);

        return dataSource;
    }

    @Bean
    public DataSource hikariDataSource(){

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        config.setMaximumPoolSize(10); //maximo de conexões liberadas
        config.setMinimumIdle(1); //minimo que vai ser liberado já de inicio

        config.setPoolName("pool-libraryapi");
        config.setMaxLifetime(600000); // 600 mil ms = 10 min;
        config.setConnectionTimeout(100000); //tempo em que ele vai tentar conectar, se não conseguir ele da timeout
        config.setConnectionTestQuery("SELECT 1"); //testar se ele está conectando com o banco

        return new HikariDataSource(config);
    }

}
