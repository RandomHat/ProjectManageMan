package com.fourthgroup.projectmanageman.utility;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MySQLConnectionPoolTest {

    @Test
    public void generalTest(){
        ConnectionPool connectionPool;

        try {
            // Arrange
            connectionPool = MySQLConnectionPool.create(
                    System.getenv("DB_URL"),
                    System.getenv("DB_USERNAME"),
                    System.getenv("DB_PASSWORD")
            );

            // Act
            Connection conn = connectionPool.getConnection();

            // Assert
            assertTrue(conn.isValid(1));


        } catch (SQLException err) {
            System.out.println("Something went wrong, connection wasn't established: " + err);
        }
    }
}