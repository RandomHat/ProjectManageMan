package com.fourthgroup.projectmanageman.utility;

import java.sql.*;
import java.util.*;

/*
    ===============================
    Author: Simon Roswall Jørgensen
    Date: Nov 26, 2021
    ===============================

    Source: https://www.baeldung.com/java-connection-pooling
 */

public class MySQLConnectionPool implements ConnectionPool {

    private String url;
    private String username;
    private String password;
    private List<Connection> pool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 5;

    public static MySQLConnectionPool create(
            String url, String username, String password)
            throws SQLException {

        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for(int i = 0; i < INITIAL_POOL_SIZE; i++){
            pool.add(createConnection(url, username, password));
        }

        return new MySQLConnectionPool(url, username, password, pool);
    }

    private MySQLConnectionPool(String url, String username, String password, List<Connection> pool){
        this.url = url;
        this.username = username;
        this.password = password;
        this.pool = pool;
    }

    @Override
    public Connection getConnection() {
        Connection connection = pool.remove(pool.size()-1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        return false;
    }

    private static Connection createConnection(
            String url, String username, String password)
            throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }


    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
