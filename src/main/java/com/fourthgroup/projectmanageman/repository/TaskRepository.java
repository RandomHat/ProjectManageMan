package com.fourthgroup.projectmanageman.repository;


import com.fourthgroup.projectmanageman.model.Status;
import com.fourthgroup.projectmanageman.model.Task;
import com.fourthgroup.projectmanageman.utility.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class TaskRepository implements IRepository<Task> {

/*
    ===============================
    Author: Simon Roswall Jørgensen
    Date: Dec 7, 2021
    ===============================
*/

    ConnectionPool connectionPool;

    @Override
    public boolean write(Task task) {
        Connection conn = connectionPool.getConnection();
        PreparedStatement preparedStatement;
        boolean isCreated = true;

        try {
            preparedStatement = conn.prepareStatement("INSERT INTO tasks(project_id, parent_task_id, title, type, description, product_description," +
                    " est_time_hours, spent_time_hours, start_date, deadline, status) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

            preparedStatement.setInt(1,task.getProjectID());
            preparedStatement.setInt(2, task.getParentTaskID());
            preparedStatement.setString(3, task.getTitle());
            preparedStatement.setString(4, task.getType());
            preparedStatement.setString(5, task.getDescription());
            preparedStatement.setString(6, task.getProductDescription());
            preparedStatement.setInt(7, task.getEstTime());
            preparedStatement.setInt(8, task.getSpentTime());
            preparedStatement.setDate(9, task.getStartDateSQL());
            preparedStatement.setDate(10,task.getStartDateSQL());
            preparedStatement.setInt(11, task.getStatus().ordinal());
            preparedStatement.executeUpdate();

        } catch (SQLException err){
            isCreated = false;
            System.out.println("SQL insert failed with error: " + err);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return isCreated;
    }

    @Override
    public Task read(int id) {
        Connection conn = connectionPool.getConnection();
        PreparedStatement preparedStatement;
        Task task = null;

        try {
            preparedStatement = conn.prepareStatement("SELECT * from tasks where task_id = (?)");
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            result.next(); //points to first object
            task = unpackSingleResult(result);

        } catch (SQLException err){
            System.out.println("SQL select failed with error: " + err);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return task;
    }

    @Override
    public boolean update(Task task) {
        Connection conn = connectionPool.getConnection();
        PreparedStatement preparedStatement;
        boolean isUpdated = true;

        try {
            preparedStatement = conn.prepareStatement("UPDATE tasks SET project_id = ?, parent_task_id = ?, " +
                    "title=?, type=?, description=?, product_description=?, est_time_hours=?, " +
                    "spent_time_hours=?, start_date=?, deadline=?, status=? WHERE task_id = ? ");

            preparedStatement.executeUpdate();
        }
        catch (SQLException err){
            System.out.println("SQL update failed with error: " + err);
            isUpdated = false;
        }
        finally{
            connectionPool.releaseConnection(conn);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Task task) {
        Connection conn = connectionPool.getConnection();
        PreparedStatement preparedStatement;
        boolean isDeleted = true;

        try {
            preparedStatement = conn.prepareStatement("DELETE FROM tasks WHERE (task_id = ?) ");
            preparedStatement.executeUpdate();

        }
        catch (SQLException err){
            System.out.println("SQL update failed with error: " + err);
            isDeleted = false;
        }
        finally{
            connectionPool.releaseConnection(conn);
        }
        return isDeleted;
    }

    public Set<Task> getProjectTasks(int projectID, Status status){
        return querySetOfTasks("SELECT * FROM tasks WHERE project_id =" + projectID +
                " AND status=" + status.ordinal() + ";") ;
    }


    public Set<Task> getProjectTasks(int projectID) {
        return querySetOfTasks("SELECT * FROM tasks WHERE project_id =" + projectID + ";");
    }

    private Set<Task> querySetOfTasks(String sqlStatement) {
            Connection conn = connectionPool.getConnection();
            PreparedStatement preparedStatement;
            Set<Task> tasks = new LinkedHashSet<>();

            try {
                preparedStatement = conn.prepareStatement(sqlStatement);
                ResultSet result = preparedStatement.executeQuery();
                while(result.next()) {
                    tasks.add(unpackSingleResult(result));
                }

            } catch (SQLException err){
                System.out.println("SQL select failed with error: " + err);
            } finally {
                connectionPool.releaseConnection(conn);
            }
            return tasks;
    }

    private Task unpackSingleResult(ResultSet result) throws SQLException{
        return new Task(
                result.getInt(1),
                result.getInt(2),
                result.getInt(3),
                result.getString(4),
                result.getString(5),
                result.getString(6),
                result.getString(7),
                result.getInt(8),
                result.getInt(9),
                result.getDate(10).toLocalDate(),
                result.getDate(11).toLocalDate(),
                Status.fromInteger(result.getInt(12))
        );
    }

    @Autowired
    public void setConnectionPool(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }
}
