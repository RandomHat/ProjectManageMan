package com.fourthgroup.projectmanageman.repository;

import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.utility.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        User currentUser = new User();

        try {
            pstmt = connection.prepareStatement("SELECT * FROM USERS");
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                currentUser = getUser(currentUser, resultSet);
                listOfUsers.add(currentUser);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        connectionPool.releaseConnection(connection);
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
            pstmt.setBoolean(7,user.isAdmin());

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

        try{
            pstmt = connection.prepareStatement("SELECT * from users where username = ? and password = ?");
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                currentUser = getUser(currentUser, resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentUser;
    }

    private User getUser(User currentUser, ResultSet resultSet) throws SQLException {
        currentUser.setId(resultSet.getInt("person_id"));
        currentUser.setFirstName(resultSet.getString("firstname"));
        currentUser.setLastName(resultSet.getString("lastname"));
        currentUser.setPhonenumber(resultSet.getString("phonenumber"));
        currentUser.setEmail(resultSet.getString("email"));
        currentUser.setUsername(resultSet.getString("username"));
        currentUser.setPassword(resultSet.getString("password"));

        return currentUser;
    }

}