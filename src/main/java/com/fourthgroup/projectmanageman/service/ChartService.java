package com.fourthgroup.projectmanageman.service;

import com.fourthgroup.projectmanageman.model.Project;
import com.fourthgroup.projectmanageman.model.Task;
import org.springframework.stereotype.Component;
import java.util.List;

/*
    ===============================
    Author: Mark Kaplan Hansen
    Date: Dec 10, 2021
    ===============================
 */

@Component
public class ChartService {

    public ChartService() {
    }

    /**
     * Makes array for ganttChartProject js to read
     * @param projectList
     * @return String array - data for diagram
     */
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

    /**
     * Makes array for ganttTaskProject js to read
     * @param userTaskList
     * @return String[][] - data for js diagram
     */
    public String[][] taskChart(List<Task> userTaskList) {
        String[] firstLineArray = {"'Task'","'Est time'","'time spent'"};
        String[][] projectArray = new String[userTaskList.size() + 1][];
        projectArray[0] = firstLineArray;
        for (int i = 0; i < userTaskList.size(); i++) {
            String[] currentProject = new String[3];
            currentProject[0] = "'" + userTaskList.get(i).getTitle() + "'";
            currentProject[1] = Integer.toString(userTaskList.get(i).getEstTime());
            currentProject[2] = Integer.toString(userTaskList.get(i).getSpentTime());
            projectArray[i + 1] = currentProject;
        }
        return projectArray;
    }
}
