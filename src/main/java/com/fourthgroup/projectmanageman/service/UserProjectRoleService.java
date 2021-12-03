package com.fourthgroup.projectmanageman.service;

import com.fourthgroup.projectmanageman.model.Project;
import com.fourthgroup.projectmanageman.model.Role;
import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.model.UserProjectRole;
import com.fourthgroup.projectmanageman.repository.ProjectEnrollmentRepository;
import com.fourthgroup.projectmanageman.repository.ProjectRepository;
import com.fourthgroup.projectmanageman.repository.RoleRepository;
import org.springframework.stereotype.Service;

/*
    ===============================
    Author: Frederik Wandall von Benzon
    Date: Dec 3, 2021
    ===============================
 */

@Service
public class UserProjectRoleService {

    private final ProjectEnrollmentRepository projectEnrollmentRepository = new ProjectEnrollmentRepository();
    private final RoleRepository roleRepository = new RoleRepository();

    public int assignUserToProject (User user, Project project, int roleId){
        int userId = user.getId();
        int projectId = project.getId();
        //RoleId: Manger = 5, participant = 15

        UserProjectRole userProjectRole = new UserProjectRole(userId, projectId, roleId);
        return projectEnrollmentRepository.assignUserToProject(userProjectRole);
    }

    public int assignManagerToProject (User user, Project project){
        int roleId = roleRepository.getRole("Manager").getId();
        return assignUserToProject(user, project, 5); //Manager = 5
    }

    public int assignParticipantToProject (User user, Project project){
        int roleId = roleRepository.getRole("Participant").getId();
        return assignUserToProject(user, project, 15); //Participant = 15
    }

}
