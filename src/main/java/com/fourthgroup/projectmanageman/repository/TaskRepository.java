package com.fourthgroup.projectmanageman.repository;


import com.fourthgroup.projectmanageman.model.Status;
import com.fourthgroup.projectmanageman.model.Task;
import com.fourthgroup.projectmanageman.utility.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
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
    public int write(Task task) {
        Connection conn = connectionPool.getConnection();
        PreparedStatement preparedStatement;
        int index = -1;
        String query = "INSERT INTO tasks(project_id, parent_task_id, title, type, description, " +
                "product_description, est_time_hours, spent_time_hours, start_date, deadline, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            setValues(preparedStatement, task);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                index = resultSet.getInt(1);
            }

        } catch (SQLException err){
            System.out.println("SQL insert failed with error: " + err);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return index;
    }

    @Override
    public Task read(int id) {
        Connection conn = connectionPool.getConnection();
        PreparedStatement preparedStatement;
        Task task = null;

        try {
            preparedStatement = conn.prepareStatement("SELECT * from tasks where task_id = ?");
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) { //points to first object
                task = unpackSingleResult(result);
            }

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
                    "title = ?, type = ?, description = ?, product_description = ?, est_time_hours = ?, " +
                    "spent_time_hours = ?, start_date = ?, deadline = ?, status=? WHERE task_id = ?");

            setValues(preparedStatement, task);
            preparedStatement.setInt(12,task.getTaskID());
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
            preparedStatement = conn.prepareStatement("DELETE FROM tasks WHERE task_id = ?");
            preparedStatement.setInt(1,task.getTaskID());
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

        // checks nullpointer with a condensed if/else (ternary operator)
        LocalDate startdate = result.getDate(10) == null ? null : result.getDate(10).toLocalDate();
        LocalDate deadline = result.getDate(11) == null ? null : result.getDate(11).toLocalDate();
        Status status = result.getInt(12) == 0 ? null : Status.fromInteger(result.getInt(12));

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
                startdate,
                deadline,
                status
        );
    }

    /**
     * Adresses null-pointer exceptions when handling integer fields with null values.
     *
     * @param pstm Only valid for an SQL statement concerning tasks, with all 11 task columns as input parameters.
     *             task_id is excluded as the value is handled by the DB (auto incremented).
     * @param task The object to get values from.
     */
    private void setValues(PreparedStatement pstm, Task task) throws SQLException{
        if (task.getProjectID() != null){
            pstm.setInt(1,task.getProjectID());
        } else {
            pstm.setNull(1, Types.INTEGER);
        }
        if (task.getParentTaskID() != null) {
            pstm.setInt(2, task.getParentTaskID());
        } else {
            pstm.setNull(2, Types.INTEGER);
        }
        pstm.setString(3, task.getTitle());
        pstm.setString(4, task.getType());
        pstm.setString(5, task.getDescription());
        pstm.setString(6, task.getProductDescription());

        if (task.getEstTime() != null){
            pstm.setInt(7, task.getEstTime());
        } else {
            pstm.setNull(7, Types.INTEGER);
        }
        if (task.getSpentTime() != null) {
            pstm.setInt(8, task.getSpentTime());
        } else {
            pstm.setNull(8,Types.INTEGER);
        }

        pstm.setDate(9, task.getStartDateSQL());
        pstm.setDate(10,task.getStartDateSQL());

        if (task.getStatus() != null) {
            pstm.setInt(11,task.getStatus().ordinal());
        } else {
            pstm.setNull(11, Types.INTEGER);
        }
    }

    @Autowired
    public void setConnectionPool(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }
}
