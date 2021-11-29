package com.fourthgroup.projectmanageman;

import com.fourthgroup.projectmanageman.utility.MySQLConnectionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Bean
    public MySQLConnectionPool mySQLConnectionPool() throws SQLException {
        return MySQLConnectionPool.create(
                System.getenv("DB_URL"),
                System.getenv("DB_USERNAME"),
                System.getenv("DB_PASSWORD")
        );
    }

    @Bean
    public IDialect java8Dialect(){
        return new Java8TimeDialect();
    }
}
