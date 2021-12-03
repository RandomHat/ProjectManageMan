package com.fourthgroup.projectmanageman.controller;

import com.fourthgroup.projectmanageman.model.Project;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.fourthgroup.projectmanageman.service.ProjectService;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

public class ProjectController {

    private ProjectService projectService = new ProjectService();

    @GetMapping("/create/create-project")
    public String createProjectGet(HttpSession session){
        //Create project form
        return "create-project";
    }

    @PostMapping("/create/create-project") // Send form
    public String createProjectPost(WebRequest projectForm, HttpSession session){
        //projectService.parseInputDate("YYYY-MM-DD"); //Parse inputdate string fra form (model- eller service-lag?)


        int projectID = projectService.writeNewProject(projectService.saveProjectForm(projectForm));

        //ProjectID = 0 hvis query fejler
        //Check id og pipe videre

        return "project-create-success";
    }

    @GetMapping("project/{projectId}")
    public String showProjectById (@PathVariable int projectId, Model model){
        Project project = projectService.getProjectById(projectId);

        model.addAttribute("project", project);

        return "single-project";
    }
}