package com.fourthgroup.projectmanageman.service;

/*
    ===============================
    Author: Frederik Wandall von Benzon
    Date: Nov 27, 2021
    ===============================
 */

import com.fourthgroup.projectmanageman.model.Project;
import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.time.DateTimeException;
import java.time.LocalDate;

@Service
public class ProjectService {

    private final ProjectRepository projectRepo = new ProjectRepository();

    public Project getProjectById (int id){
        return projectRepo.getProjectById(id);
    }

    public Project saveProjectForm (WebRequest projectForm){
        Project project = new Project();
        project.setTitle(projectForm.getParameter("projectname"));
        project.setDescription(projectForm.getParameter("projectdescription"));
        project.setDeadline(parseInputDate(projectForm.getParameter("deadline")));

        return project;
    }


    public int writeNewProject (Project project){
        Project newProject = new Project();
        int projectID = 0;

        projectID = projectRepo.writeNewProjectComplete(project);
        return projectID;
    }

    public LocalDate parseInputDate (String dateTime) {
        try {
            return LocalDate.parse(dateTime.substring(0,10)); //YYYY-MM-DDTHH-MM-SS -> YYYY-MM-DD
        }
        catch (DateTimeException e){
            System.out.println("Dateformat was borked. Default to current date " + e.getMessage());
            return LocalDate.now();
        }
    }
}