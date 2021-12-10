package com.fourthgroup.projectmanageman.repository;

import com.fourthgroup.projectmanageman.model.*;
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
    Date: Dec 3, 2021
    ===============================
 */

@Component
public class ProjectEnrollmentRepository {

    @Autowired
    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    ConnectionPool connectionPool;
    List<Project> projectList;
    List<User> userList;
    List<Role> roleList;

    public int assignUserToProject(UserProjectRole userProjectRole) {
        Connection connection = connectionPool.getConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = connection.prepareStatement("INSERT INTO project_enrollment (user_id, project_id, role_id) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, userProjectRole.getUserId());
            pstmt.setInt(2, userProjectRole.getProjectId());
            pstmt.setInt(3, userProjectRole.getRoleId());

            connectionPool.releaseConnection(connection);
            return pstmt.executeUpdate(); //Returns 1 if inserted row

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connectionPool.releaseConnection(connection);
            return 0;
        }
    }

    public List<AssignedProjectUsers> getUsersAssignedToProject(int projectId) {
        Connection connection = connectionPool.getConnection();
        List<AssignedProjectUsers> listOfEnrollments = new ArrayList<>();
        PreparedStatement pstmt = null;

        try {
            pstmt = connection.prepareStatement("SELECT p.project_id, u.user_id, u.firstname, u.lastname, r.name from project_enrollments p LEFT JOIN users u ON u.user_id = p.user_id LEFT JOIN roles r on p.role_id = r.role_id WHERE p.project_id = ?;");
            pstmt.setInt(1, projectId);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                AssignedProjectUsers assigned = new AssignedProjectUsers();
                assigned.setProjectId(resultSet.getInt("project_id"));
                assigned.setUserId(resultSet.getInt("user_id"));
                assigned.setFirstName(resultSet.getString("firstname"));
                assigned.setLastName(resultSet.getString("lastname"));
                assigned.setRoleName(resultSet.getString("name"));

                listOfEnrollments.add(assigned);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        connectionPool.releaseConnection(connection);
        return listOfEnrollments;
    }

    public List<UserProjectRole> getAllEnrollments() {
        Connection connection = connectionPool.getConnection();
        List<UserProjectRole> listOfEnrollments = new ArrayList<>();
        PreparedStatement pstmt = null;

        try {
            pstmt = connection.prepareStatement("SELECT * FROM project_enrollments");
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                UserProjectRole current = new UserProjectRole();
                current.setId(resultSet.getInt("project_enrollments_id"));
                current.setUserId(resultSet.getInt("user_id"));
                current.setProjectId(resultSet.getInt("project_id"));
                current.setRoleId(resultSet.getInt("role_id"));

                listOfEnrollments.add(current);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        connectionPool.releaseConnection(connection);
        return listOfEnrollments;
    }

    public List<Project> getProjectsByUserId(int id) {
        Connection connection = connectionPool.getConnection();
        List<Project> listOfProjects = new ArrayList<>();
        PreparedStatement pstmt = null;

        try {
            pstmt = connection.prepareStatement("SELECT * FROM projects WHERE project_id IN (SELECT project_id FROM project_enrollments WHERE user_id = (?))");
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                Project currentProject = new Project();
                currentProject.setId(resultSet.getInt("project_id"));
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
        connectionPool.releaseConnection(connection);
        return listOfProjects;
    }

}
