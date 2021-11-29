package com.fourthgroup.projectmanageman.repository;

import com.fourthgroup.projectmanageman.model.Role;
import com.fourthgroup.projectmanageman.utility.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRepository {

    @Autowired
    ConnectionPool connectionPool;

    public Role getManagerRole(){
        Role projectManager = new Role();

        PreparedStatement pstmt = null;

        try{
            pstmt = connectionPool.getConnection().prepareStatement("select * from Roles where name = 'project manager'");
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                projectManager.setId(resultSet.getInt("role_id"));
                projectManager.setRoleName(resultSet.getString("name"));
                projectManager.setRoleDescription(resultSet.getString("description"));
            return projectManager;
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return projectManager;
    }

    public Role getParticipantRole(){
        Role projectParticipant = new Role();

        PreparedStatement pstmt = null;

        try{
            pstmt = connectionPool.getConnection().prepareStatement("select * from Roles where name = 'project participant'");
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                projectParticipant.setId(resultSet.getInt("role_id"));
                projectParticipant.setRoleName(resultSet.getString("name"));
                projectParticipant.setRoleDescription(resultSet.getString("description"));
                return projectParticipant;
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return projectParticipant;
    }
}
