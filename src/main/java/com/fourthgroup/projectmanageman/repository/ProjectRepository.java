package com.fourthgroup.projectmanageman.repository;

import com.fourthgroup.projectmanageman.model.Project;
import com.fourthgroup.projectmanageman.model.Role;
import com.fourthgroup.projectmanageman.model.Status;
import com.fourthgroup.projectmanageman.utility.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
    ===============================
    Author: Frederik Wandall von Benzon
    Date: Nov 26, 2021
    ===============================
 */

@Component
public class ProjectRepository {
    //@Autowired
    ConnectionPool connectionPool;
    List<Project> projectList;

    public Project getProjectById(int id) {
        Connection connection = connectionPool.getConnection();
        Project project = new Project();
        PreparedStatement pstmt = null;

        try {
            pstmt = connection.prepareStatement("SELECT * FROM projects where project_id = ?");
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                project.setParentProjectID(resultSet.getInt("parent_project_id"));
                project.setTitle(resultSet.getString("title"));
                project.setStatus(Status.fromInteger(resultSet.getInt("status")));
                project.setDescription(resultSet.getString("description"));
                project.setClient(resultSet.getString("client"));
                project.setStartDate(LocalDate.parse(resultSet.getString("startdate"))); //Parse sqldate to Localdate
                project.setDeadline(LocalDate.parse(resultSet.getString("deadline")));
                project.setEstTimeHours(resultSet.getInt("est_time_hours"));
                project.setSpentTimeHours(resultSet.getInt("spent_hours"));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        connectionPool.releaseConnection(connection);
        return project;
    }

    public List<Project> getAllProjects() {
        List<Project> listOfProjects = new ArrayList<>();
        PreparedStatement pstmt = null;

        try {
            pstmt = connectionPool.getConnection().prepareStatement("SELECT * FROM PROJECTS");
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                Project currentProject = new Project();
                currentProject.setParentProjectID(resultSet.getInt("parent_project_id"));
                currentProject.setTitle(resultSet.getString("title"));
                currentProject.setStatus(Status.fromInteger(resultSet.getInt("status")));
                currentProject.setDescription(resultSet.getString("description"));
                currentProject.setClient(resultSet.getString("client"));
                currentProject.setStartDate(LocalDate.parse(resultSet.getString("startdate"))); //Parse sqldate to Localdate
                currentProject.setDeadline(LocalDate.parse(resultSet.getString("deadline")));
                currentProject.setEstTimeHours(resultSet.getInt("est_time_hours"));
                currentProject.setSpentTimeHours(resultSet.getInt("spent_hours"));

                listOfProjects.add(currentProject);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listOfProjects;
    }

    public int writeNewProjectComplete(Project project) {
        PreparedStatement pstmt = null;

        try {
            pstmt = connectionPool.getConnection().prepareStatement("INSERT INTO projects (parent_project_id, status, title, startdate, client, deadline, est_time_hours, spent_hours, description) VALUES(?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, project.getParentProjectID());
            pstmt.setInt(2, project.getStatusAsInt());
            pstmt.setString(3, project.getTitle());
            pstmt.setDate(4, project.getStartDateSQL());
            pstmt.setString(5, project.getClient());
            pstmt.setDate(6, project.getDeadlineSQL());
            pstmt.setInt(7, project.getEstTimeHours()); //Passer ikke overens med DB type
            pstmt.setInt(8, project.getSpentTimeHours()); // ----
            pstmt.setString(9, project.getDescription());

            return pstmt.executeUpdate(); //Returns project_id key for inserted project akin to "SELECT LAST_INSERT_ID()"

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Autowired
    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
