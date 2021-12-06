package com.fourthgroup.projectmanageman.repository;

import com.fourthgroup.projectmanageman.model.Role;
import com.fourthgroup.projectmanageman.utility.ConnectionPool;
import com.fourthgroup.projectmanageman.utility.MySQLConnectionPool;
import com.mysql.cj.jdbc.exceptions.SQLError;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/*
    ===============================
    Author: Mark Kaplan Hansenproject_enrollments
    Date: Nov 29, 2021
    ===============================
 */

class RoleRepositoryTest {

    @Test
    void getRole() {
        ConnectionPool connectionPool;
        try {
            connectionPool = MySQLConnectionPool.create(System.getenv("JAWS_DB"));
            RoleRepository roleRepository = new RoleRepository();
            roleRepository.setConnectionPool(connectionPool);
            //arrange
            String roleName = "Project Participant";
            String roleNameTwo = "Project";
            Role role;
            Role wrongRole;

            //Act

            role = roleRepository.getRole(roleName);
            wrongRole = roleRepository.getRole(roleNameTwo);

            //Assert

            assertTrue(role.getRoleName().equals(roleName));
            assertTrue(wrongRole.getRoleName() == null);

        } catch (SQLException e){System.out.println("something went wrong in DB: " + e);}
        catch (URISyntaxException e) {System.out.println("URI syntax failed: " + e);}
    }


}