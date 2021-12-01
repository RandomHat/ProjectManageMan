package com.fourthgroup.projectmanageman.repository;

import com.fourthgroup.projectmanageman.model.Role;
import com.fourthgroup.projectmanageman.utility.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
    ===============================
    Author: Mark Kaplan Hansen
    Date: Nov 29, 2021
    ===============================
 */

@Component
public class RoleRepository {

    ConnectionPool connectionPool;

    public Role getRole(String specificRole) {
        Connection connection = connectionPool.getConnection();
        Role role = new Role();
        PreparedStatement pstmt = null;

        try {

            pstmt = connection.prepareStatement("select * from Roles where name = ?");

            pstmt.setString(1, specificRole);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                role.setId(resultSet.getInt("role_id"));
                role.setRoleName(resultSet.getString("name"));
                role.setRoleDescription(resultSet.getString("description"));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
            return role;
        }
    }

    @Autowired
    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
