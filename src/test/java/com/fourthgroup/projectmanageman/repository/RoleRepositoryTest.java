package com.fourthgroup.projectmanageman.repository;

import com.fourthgroup.projectmanageman.model.Role;
import com.fourthgroup.projectmanageman.utility.ConnectionPool;
import com.fourthgroup.projectmanageman.utility.MySQLConnectionPool;
import com.mysql.cj.jdbc.exceptions.SQLError;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RoleRepositoryTest {

    @Test
    void getRole() {
        ConnectionPool connectionPool;
        try {
            connectionPool = MySQLConnectionPool.create(
                    System.getenv("DB_URL"),
                    System.getenv("DB_USERNAME"),
                    System.getenv("DB_PASSWORD")
            );
            RoleRepository roleRepository = new RoleRepository();
            roleRepository.setConnectionPool(connectionPool);
            //arrange
            String roleName = "Project participant";
            Role role;

            //Act

            role = roleRepository.getRole(roleName);

            //Assert

            assertTrue(role.getRoleName().equals(roleName));
        } catch (SQLException e){

        }
    }


}