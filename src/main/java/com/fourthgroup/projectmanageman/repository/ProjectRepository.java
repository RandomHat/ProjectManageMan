package com.fourthgroup.projectmanageman.repository;

import com.fourthgroup.projectmanageman.model.Project;
import com.fourthgroup.projectmanageman.model.Status;
import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.utility.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
    @Autowired
    ConnectionPool connectionPool;

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
}
