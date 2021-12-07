package com.fourthgroup.projectmanageman.service;

import com.fourthgroup.projectmanageman.model.Project;
import com.fourthgroup.projectmanageman.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChartService {

    public ChartService() {
    }

    public String[][] projectChart(List<Project> projectList){
        String[] firstLineArray = {"'Project'","'Est time'","'time spent'"};
        String[][] projectArray = new String[projectList.size() + 1][];
        projectArray[0] = firstLineArray;
        for (int i = 0; i < projectList.size(); i++) {
            String[] currentProject = new String[3];
            currentProject[0] = "'" + projectList.get(i).getTitle() + "'";
            currentProject[1] = Integer.toString(projectList.get(i).getEstTimeHours());
            currentProject[2] = Integer.toString(projectList.get(i).getSpentTimeHours());
            projectArray[i + 1] = currentProject;
        }

        return projectArray;
    }
}
