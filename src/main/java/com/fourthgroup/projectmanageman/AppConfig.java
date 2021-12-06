package com.fourthgroup.projectmanageman;

import com.fourthgroup.projectmanageman.utility.ConnectionPool;
import com.fourthgroup.projectmanageman.utility.MySQLConnectionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;
import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Bean
    public ConnectionPool mySQLConnectionPool() throws SQLException {
        ConnectionPool connectionPool = null;
        try {
            connectionPool = MySQLConnectionPool.create(System.getenv("JAWSDB_URL"));
        } catch (URISyntaxException err){
            System.out.println("Something went wrong could not parse URI: " + err);
        }
        return connectionPool;
    }
}
