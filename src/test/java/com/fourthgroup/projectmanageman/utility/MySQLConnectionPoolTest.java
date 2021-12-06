package com.fourthgroup.projectmanageman.utility;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MySQLConnectionPoolTest {

    static MySQLConnectionPool connectionPool;

    @BeforeAll
    static void setupAll(){
        try {
            // Arrange
            connectionPool = MySQLConnectionPool.create(System.getenv("JAWSDB_URL"));

        } catch (SQLException err) {
            System.out.println("Something went wrong, connection wasn't established: " + err);
        } catch (URISyntaxException err){
            System.out.println("URI could not be read: " + err);
        }

    }

    @Test
    public void getAndReleaseTest(){
        try {
            // Act
            Connection single = connectionPool.getConnection();

            // Assert
            assertTrue(single.isValid(1));

            //Act
            boolean status = connectionPool.releaseConnection(single);

            //Assert
            assertTrue(status);


        } catch (SQLException err) {
            System.out.println("Something went wrong: " + err);
        }
    }

    @Test
    public void increaseAndDecreaseAutomaticallyTest(){

            //Arrange
            Connection[] conn = new Connection[10];
            boolean[] expected = new boolean[10];
            Arrays.fill(expected,Boolean.TRUE);
            boolean[] validConnections = new boolean[10];
            boolean[] releasedConnections = new boolean[10];

        try {
            for (int i = 0; i < conn.length; i++) {
                conn[i] = connectionPool.getConnection();
                validConnections[i] = conn[i].isValid(1);
            }

            for (int i = 0; i<releasedConnections.length; i++){
                releasedConnections[i] = connectionPool.releaseConnection(conn[i]);
            }
        } catch (SQLException err){
            System.out.println("something went wrong: " + err);
        }

        assertArrayEquals(expected, validConnections);
        assertArrayEquals(expected,releasedConnections);
        assertEquals(5,connectionPool.getAvailableConnectionsCount());
        assertEquals(0, connectionPool.getUsedConnectionsCount());
    }
}