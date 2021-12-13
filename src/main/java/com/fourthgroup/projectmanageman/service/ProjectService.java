package com.fourthgroup.projectmanageman.service;

/*
    ===============================
    Author: Frederik Wandall von Benzon
    Date: Nov 27, 2021
    ===============================
 */

import com.fourthgroup.projectmanageman.model.Project;
import com.fourthgroup.projectmanageman.model.Status;
import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private final ProjectRepository projectRepo = new ProjectRepository();


    public Project getProjectById (int id){
        return projectRepo.getProjectById(id);
    }

    public List<Project> getProjectsByUserId (int id){
        return projectRepo.getProjectsByUserId(id);
    }

    public List<Project> getAllProjects (){
        return projectRepo.getAllProjects();
    }

    public Project saveProjectForm (WebRequest projectForm){
        Project project = new Project();
        project.setTitle(projectForm.getParameter("projecttitle"));
        project.setStartDate(project.parseInputDate(projectForm.getParameter("startdate")));
        project.setDeadline(project.parseInputDate(projectForm.getParameter("deadline")));
        project.setClient(projectForm.getParameter("projectclient"));
        project.setDescription(projectForm.getParameter("projectdescription"));
        project.setParentProjectID(111); //How to self reference?
        project.setStatus(Status.PENDING);
        return project;
    }

    public boolean deleteProject (int projectId){
        return projectRepo.deleteProject(projectId);
    }


    public int writeNewProject (Project project){
        int projectID = 0;

        projectID = projectRepo.writeNewProjectComplete(project);
        return projectID;
    }
}
