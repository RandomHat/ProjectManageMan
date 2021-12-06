package com.fourthgroup.projectmanageman.utility;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.*;

/*
    ===============================
    Author: Simon Roswall Jørgensen
    Original creation Date: Nov 27, 2021
    Last update: Dec 3, 2021
    Notes: Changed provider to JawsDB, refactored class to connect using URI/URL only.
    ===============================

    Source: https://www.baeldung.com/java-connection-pooling
 */

public class MySQLConnectionPool implements ConnectionPool {

    private final String url;
    private final String username;
    private final String password;
    private final List<Connection> availableConnections;
    private final List<Connection> usedConnections = new ArrayList<>();
    private final static int INITIAL_POOL_SIZE = 5;
    private static int CURRENT_POOL_SIZE = 5;
    private final static int MAXIMUM_POOL_SIZE = 10;

    public static MySQLConnectionPool create(String URL)
            throws URISyntaxException, SQLException {

        URI jdbUri = new URI(URL);
        String username = jdbUri.getUserInfo().split(":")[0];
        String password = jdbUri.getUserInfo().split(":")[1];
        String port = String.valueOf(jdbUri.getPort());
        String jdbUrl = "jdbc:mysql://" +jdbUri.getHost() + ":" + port + jdbUri.getPath();

        List<Connection> availableConnections = new ArrayList<>(INITIAL_POOL_SIZE);

        for(int i = 0; i < INITIAL_POOL_SIZE; i++){
            availableConnections.add(createConnection(jdbUrl, username, password));
        }

        return new MySQLConnectionPool(jdbUrl, username, password, availableConnections);
    }

    private MySQLConnectionPool(String url, String username, String password,
                                List<Connection> availableConnections){
        this.url = url;
        this.username = username;
        this.password = password;
        this.availableConnections = availableConnections;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        if (availableConnections.size() == 0 && CURRENT_POOL_SIZE < MAXIMUM_POOL_SIZE){
            try {
                connection = addConnection();
            } catch (SQLException err) {
                System.out.println("Attempted to increase connection pool, " +
                        "something went wrong: " + err);
            }
        } else {
            connection = availableConnections.remove(availableConnections.size() - 1);
            try {
                if (!connection.isValid(1)){
                    connection = addConnection();
                }
            } catch (SQLException err) {
                    System.out.println("Connection was inactive, something went wrong" +
                            " when a substitute was attempted: " + err);
                }
        } if (connection != null) {
            usedConnections.add(connection);
        }
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        boolean status = false;
        if (CURRENT_POOL_SIZE > INITIAL_POOL_SIZE && availableConnections.size() > 1){
            try {
                status = usedConnections.remove(connection);
                dropConnection(connection);
            } catch (SQLException err){
                System.out.println("Tried closing extraneous connection, " +
                        "however something went wrong: " + err);
            }
        } else {
            availableConnections.add(connection);
            status = usedConnections.remove(connection);
        }
        return status;
    }

    public int getAvailableConnectionsCount(){
        return this.availableConnections.size();
    }

    public int getUsedConnectionsCount(){
        return this.usedConnections.size();
    }

    private Connection addConnection() throws SQLException{
        CURRENT_POOL_SIZE++;
        return createConnection(url, username,password);
    }

    private void dropConnection(Connection connection) throws SQLException{
        connection.close();
        CURRENT_POOL_SIZE--;
    }

    private static Connection createConnection(
            String url, String username, String password)
            throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
