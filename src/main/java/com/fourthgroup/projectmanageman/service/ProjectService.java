package com.fourthgroup.projectmanageman.service;

// Fred

import com.fourthgroup.projectmanageman.model.Project;
import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;

@Service
public class ProjectService {

    private final ProjectRepository projectRepo = new ProjectRepository();

    public Project getProjectById (int id){
        return projectRepo.getProjectById(id);
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
