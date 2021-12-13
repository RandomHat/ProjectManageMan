package com.fourthgroup.projectmanageman.repository;

import com.fourthgroup.projectmanageman.model.Task;
import com.fourthgroup.projectmanageman.utility.MySQLConnectionPool;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.net.URISyntaxException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskRepositoryTest {
    private static int TASK_ID;
    private static Task TASK;

    @Test
    @Order(1)
    void write() {

        //Arrange
        TaskRepository repository = null;
        Task task = new Task();
        task.setProjectID(5);
        try {
            repository = new TaskRepository();
            repository.setConnectionPool(MySQLConnectionPool.create(System.getenv("JAWSDB_URL")));
        } catch (URISyntaxException err){
            System.out.println("URI could not be parsed: "+ err);
        } catch (SQLException err){
            System.out.println("SQL error: " + err);
        }
        //Act
        TASK_ID = repository.write(task);

        //Assert
        assertTrue(TASK_ID != -1);
    }

    @Test
    @Order(2)
    void read() {
        //Arrange
        TaskRepository repository = null;
        try {
            repository = new TaskRepository();
            repository.setConnectionPool(MySQLConnectionPool.create(System.getenv("JAWSDB_URL")));
        } catch (URISyntaxException err){
            System.out.println("URI could not be parsed: "+ err);
        } catch (SQLException err){
            System.out.println("SQL error: " + err);
        }
        //Act
        TASK = repository.read(TASK_ID);

        //Assert
        assertEquals(5, TASK.getProjectID());
    }

    @Test
    @Order(3)
    void update() {
        //Arrange
        TaskRepository repository = null;
        try {
            repository = new TaskRepository();
            repository.setConnectionPool(MySQLConnectionPool.create(System.getenv("JAWSDB_URL")));
        } catch (URISyntaxException err){
            System.out.println("URI could not be parsed: "+ err);
        } catch (SQLException err){
            System.out.println("SQL error: " + err);
        }
        boolean result;
        TASK.setType("derp");

        //Act
        result = repository.update(TASK);

        //Assert
        assertTrue(result);
        assertEquals("derp", repository.read(TASK_ID).getType());
    }

    @Test
    @Order(4)
    void delete() {
        //Arrange
        TaskRepository repository = null;
        try {
            repository = new TaskRepository();
            repository.setConnectionPool(MySQLConnectionPool.create(System.getenv("JAWSDB_URL")));
        } catch (URISyntaxException err){
            System.out.println("URI could not be parsed: "+ err);
        } catch (SQLException err){
            System.out.println("SQL error: " + err);
        }

        assertTrue(repository.delete(TASK));
    }
}