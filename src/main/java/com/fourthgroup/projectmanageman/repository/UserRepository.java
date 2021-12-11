package com.fourthgroup.projectmanageman.repository;

import com.fourthgroup.projectmanageman.model.Project;
import com.fourthgroup.projectmanageman.model.Status;
import com.fourthgroup.projectmanageman.model.Task;
import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.utility.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    ===============================
    Author: Mark Kaplan Hansen
    Date: Nov 29, 2021
    ===============================
 */
@Component
public class UserRepository {

    ConnectionPool connectionPool;

    @Autowired
    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public List<User> getAllUsers() {
        Connection connection = connectionPool.getConnection();
        List<User> listOfUsers = new ArrayList<>();
        PreparedStatement pstmt = null;
        User currentUser;

        try {
            pstmt = connection.prepareStatement("SELECT * FROM USERS");
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                currentUser = getUser(resultSet);
                listOfUsers.add(currentUser);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return listOfUsers;
    }

    public boolean writeUser(User user) {
        Connection connection = connectionPool.getConnection();
        PreparedStatement pstmt = null;
        boolean isCreated = true;
        try {
            pstmt = connection.prepareStatement("INSERT INTO USERS (firstname,lastname,phonenumber,email,username,password,is_admin) VALUES(?,?,?,?,?,?,?)");
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getPhonenumber());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getUsername());
            pstmt.setString(6, user.getPassword());
            pstmt.setBoolean(7, user.isAdmin());

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            isCreated = false;
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return isCreated;
    }


    public User userLogin(String username, String password) {
        Connection connection = connectionPool.getConnection();
        PreparedStatement pstmt = null;
        User currentUser = new User();

        try {
            pstmt = connection.prepareStatement("SELECT * from users where username = ? and password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                currentUser = getUser(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return currentUser;
    }

    private User getUser(ResultSet resultSet) throws SQLException {

        User currentUser = new User();
        currentUser.setId(resultSet.getInt("user_id"));
        currentUser.setFirstName(resultSet.getString("firstname"));
        currentUser.setLastName(resultSet.getString("lastname"));
        currentUser.setPhonenumber(resultSet.getString("phonenumber"));
        currentUser.setEmail(resultSet.getString("email"));
        currentUser.setUsername(resultSet.getString("username"));
        currentUser.setPassword(resultSet.getString("password"));
        currentUser.setAdmin(resultSet.getBoolean("is_admin"));

        return currentUser;
    }

    public List<Project> userProjectList(int userID) {
        Connection connection = connectionPool.getConnection();
        PreparedStatement pstmt = null;
        List<Project> userProjects = new ArrayList<>();

        try {
            pstmt = connection.prepareStatement("SELECT p.* from project_enrollments " +
                    "JOIN projects p ON project_enrollments.project_id = p.project_id " +
                    "where user_id = ?");
            pstmt.setInt(1, userID);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                Project currentProject = new Project();
                currentProject.setId(resultSet.getInt("project_id"));
                currentProject.setParentProjectID(resultSet.getInt("parent_project_id"));
                currentProject.setTitle(resultSet.getString("title"));
                currentProject.setStatus(Status.fromInteger(resultSet.getInt("status")));
                currentProject.setDescription(resultSet.getString("description"));
                currentProject.setClient(resultSet.getString("client"));
                currentProject.setStartDate(LocalDate.parse(resultSet.getString("startdate")));
                currentProject.setDeadline(LocalDate.parse(resultSet.getString("deadline")));
                currentProject.setEstTimeHours(resultSet.getInt("est_time_hours"));
                currentProject.setSpentTimeHours(resultSet.getInt("spent_hours"));

                userProjects.add(currentProject);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return userProjects;
    }

    public List<Task> userTaskList(int userID) {
        Connection connection = connectionPool.getConnection();
        PreparedStatement pstmt = null;
        List<Task> userTasks = new ArrayList<>();

        try {
            pstmt = connection.prepareStatement("SELECT t.* from assignments " +
                    "JOIN tasks t ON assignments.task_id = t.task_id " +
                    "where user_id = ?");
            pstmt.setInt(1, userID);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                Task currentTask = new Task(
                        resultSet.getInt("task_id"),
                        resultSet.getInt("project_id"),
                        resultSet.getInt("parent_task_id"),
                        resultSet.getString("title"),
                        resultSet.getString("type"),
                        resultSet.getString("description"),
                        resultSet.getString("product_description"),
                        resultSet.getInt("est_time_hours"),
                        resultSet.getInt("spent_time_hours"),
                        LocalDate.parse(resultSet.getString("start_date")),
                        LocalDate.parse(resultSet.getString("deadline")),
                        Status.fromInteger(resultSet.getInt("status")));
                userTasks.add(currentTask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            connectionPool.releaseConnection(connection);
        }
        return userTasks;
    }


}