package com.fourthgroup.projectmanageman.repository;

import com.fourthgroup.projectmanageman.utility.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class RepositoryDemo {

    @Autowired
    ConnectionPool connectionPool;

    public void sqlmethod(){
        Connection conn = connectionPool.getConnection();
        String sql = "select * herpderp;" ;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeQuery();
        } catch (SQLException err){
            System.out.println("Error in sql query: " + err);
        }
        connectionPool.releaseConnection(conn);
    }


}
