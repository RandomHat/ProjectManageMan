package com.fourthgroup.projectmanageman.repository;

import com.fourthgroup.projectmanageman.model.Task;
import com.fourthgroup.projectmanageman.utility.ConnectionPool;
import com.fourthgroup.projectmanageman.utility.MySQLConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class TaskRepository implements IRepository<Task> {

    ConnectionPool connectionPool;

    @Autowired
    public void setConnectionPool(MySQLConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean write(Task task) {
        Connection conn = connectionPool.getConnection();
        PreparedStatement preparedStatement;
        boolean isCreated = false;

        try {
            preparedStatement = conn.prepareStatement("INSERT INTO tasks ()");
            for (int i = 1; i < i++)
            preparedStatement.setString(i, task.);
            isCreated = preparedStatement.execute();
        } catch (SQLException err){
            System.out.println("SQL insert failed with error: " + err);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return isCreated;
    }

    @Override
    public Task read(int id) {
        return null;
    }

    @Override
    public boolean update(Task task) {
        return false;
    }

    @Override
    public boolean delete(Task task) {
        return false;
    }
}
