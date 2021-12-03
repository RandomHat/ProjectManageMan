package com.fourthgroup.projectmanageman.service;

import com.fourthgroup.projectmanageman.model.Project;
import com.fourthgroup.projectmanageman.model.Role;
import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.model.UserProjectRole;
import com.fourthgroup.projectmanageman.repository.ProjectEnrollmentRepository;
import com.fourthgroup.projectmanageman.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProjectRoleService {

    private final ProjectEnrollmentRepository projectEnrollmentRepository = new ProjectEnrollmentRepository();

    public int assignUserToProject (User user, Project project, Role role){
        return projectEnrollmentRepository.assignUserToProject(user, project, role);
    }
}
