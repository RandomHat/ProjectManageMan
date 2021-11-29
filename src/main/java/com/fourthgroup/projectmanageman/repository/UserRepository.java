package com.fourthgroup.projectmanageman.repository;

import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.utility.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
    ===============================
    Author: Mark Kaplan Hansen
    Date: Nov 29, 2021
    ===============================
 */

public class UserRepository {

    @Autowired
    ConnectionPool connectionPool;

    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>();
        PreparedStatement pstmt = null;

        try {
            pstmt = connectionPool.getConnection().prepareStatement("SELECT * FROM USERS");
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                User currentUser = new User();
                currentUser.setId(resultSet.getInt("person_id"));
                currentUser.setFirstname(resultSet.getString("firstname"));
                currentUser.setLastname(resultSet.getString("lastname"));
                currentUser.setPhonenumber(resultSet.getString("phonenum"));
                currentUser.setEmail(resultSet.getString("email"));
                currentUser.setUsername(resultSet.getString("username"));
                currentUser.setPassword(resultSet.getString("password"));
                listOfUsers.add(currentUser);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listOfUsers;
    }

    public boolean addUserToDB(User user) {
        PreparedStatement pstmt = null;

        try {
            pstmt = connectionPool.getConnection().prepareStatement("INSERT INTO USERS (firstname,lastname,phonenum,email,username,password) VALUS(?,?,?,?,?,?)");
            pstmt.setString(1, user.getFirstname());
            pstmt.setString(2, user.getLastname());
            pstmt.setString(3, user.getPhonenumber());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getUsername());
            pstmt.setString(6, user.getPassword());
            return pstmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}