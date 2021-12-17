package com.fourthgroup.projectmanageman.service;

import com.fourthgroup.projectmanageman.model.*;
import com.fourthgroup.projectmanageman.repository.ProjectEnrollmentRepository;
import com.fourthgroup.projectmanageman.repository.ProjectRepository;
import com.fourthgroup.projectmanageman.repository.RoleRepository;
import com.fourthgroup.projectmanageman.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

/*
    ===============================
    Author: Frederik Wandall von Benzon
    Date: Dec 3, 2021
    ===============================
 */

@Service
public class UserProjectRoleService {

    ProjectEnrollmentRepository projectEnrollmentRepository;
    RoleRepository roleRepository;

    @Autowired
    public void setRepository(ProjectEnrollmentRepository projectEnrollmentRepository){
        this.projectEnrollmentRepository = projectEnrollmentRepository;
    }
    @Autowired
    public void setRepository(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }


    public List<AssignedProjectUsers> getUsersAssignedToProject (int projectId) {
        return projectEnrollmentRepository.getUsersAssignedToProject(projectId);
    }

    public int assignUserToProject (User user, Project project, int roleId){
        int userId = user.getId();
        int projectId = project.getId();
        //RoleId: Manger = 1, participant = 2

        UserProjectRole userProjectRole = new UserProjectRole(userId, projectId, roleId);
        return projectEnrollmentRepository.assignUserToProject(userProjectRole);
    }

    public int assignManagerToProject (User user, Project project){
        int roleId = roleRepository.getRole("Manager").getId();
        return assignUserToProject(user, project, 1); //Manager = 1
    }

    public int assignParticipantToProject (User user, Project project){
        int roleId = roleRepository.getRole("Participant").getId();
        return assignUserToProject(user, project, 2); //Participant = 2
    }

}
