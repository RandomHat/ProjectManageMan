package com.fourthgroup.projectmanageman.repository;

import com.fourthgroup.projectmanageman.model.Project;
import com.fourthgroup.projectmanageman.model.Role;
import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.model.UserProjectRole;
import com.fourthgroup.projectmanageman.service.UserProjectRoleService;
import com.fourthgroup.projectmanageman.utility.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/*
    ===============================
    Author: Frederik Wandall von Benzon
    Date: Dec 3, 2021
    ===============================
 */

@Component
public class ProjectEnrollmentRepository {
    ConnectionPool connectionPool;
    List<Project> projectList;
    List<User> userList;
    List<Role> roleList;

    public int assignUserToProject(UserProjectRole userProjectRole) {
        PreparedStatement pstmt = null;

        try {
            pstmt = connectionPool.getConnection().prepareStatement("INSERT INTO project_enrollment (user_id, project_id, role_id) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, userProjectRole.getPersonId());
            pstmt.setInt(2, userProjectRole.getProjectId());
            pstmt.setInt(3, userProjectRole.getRoleId());

            return pstmt.executeUpdate(); //Returns 1 if inserted row

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
