package com.fourthgroup.projectmanageman.controller;

import com.fourthgroup.projectmanageman.model.Project;
import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.service.UserProjectRoleService;
import com.fourthgroup.projectmanageman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.fourthgroup.projectmanageman.service.ProjectService;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.List;

/*
    ===============================
    Author: Frederik Wandall von Benzon
    Date: Dec 1, 2021
    ===============================
 */

@Controller
public class ProjectController {

    ProjectService projectService;
    UserProjectRoleService userProjectRoleService;

    @Autowired
    public void setProjectService(ProjectService projectService){
        this.projectService = projectService;
    }
    @Autowired
    public void setUserProjectRoleService(UserProjectRoleService userProjectRoleService){
        this.userProjectRoleService = userProjectRoleService;
    }


    @GetMapping("/create-project")
    public String createProjectGet(HttpSession session){
        return "CreateProject";
    }

    @PostMapping("/create-project")
    public String createProjectForm(WebRequest projectForm){

        //             |Kald writeNewProject (Project) |Indeholder det parsede webrequest, nu som Project
        int projectID = projectService.writeNewProject(projectService.saveProjectForm(projectForm));

        System.out.println("Created project with ID" + projectID); // debug :)

        if (projectID > 0){
            System.out.println(projectService.getProjectById(projectID).toString());
            return "redirect:/show-all-projects";
        }

        return "redirect:/";
    }

    @GetMapping("/show-all-projects")
    public String showAllProjects (Model model){
        List<Project> projectList = projectService.getAllProjects();
        model.addAttribute("projectList", projectList);

        return "ShowAllProjects";
    }

    @GetMapping("/project/{projectId}")
    public String showProjectById (@PathVariable int projectId, Model model){
        Project project = projectService.getProjectById(projectId);

        model.addAttribute("project", project);

        return "single-project";
    }

    @PostMapping("/project/{projectId}/assign-manager")
    public String assignProjectManager (@PathVariable int projectId, User user){
        Project project = projectService.getProjectById(projectId);

        int a = userProjectRoleService.assignManagerToProject(user, project);

        return "assign-manager";
    }

    @PostMapping("/project/{projectId}/assign-participant")
    public String assignProjectParticipant (@PathVariable int projectId, User user){
        Project project = projectService.getProjectById(projectId);

        int a = userProjectRoleService.assignParticipantToProject(user, project);
        return "assign-participant";
    }

}
