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


    public String[][] projectGanttChart(List<Project> projectList){
        String[][] dataArrays = new String[projectList.size()][];

        for (int i = 0; i < projectList.size() ; i++){
            String[] currentProject = new String[8];
            //project ID
            currentProject[0] = Integer.toString(projectList.get(i).getId());
            //Project title
            currentProject[1] = projectList.get(i).getTitle();
            // project id or parentId id
            if(projectList.get(i).getParentProjectID() > 0){
                currentProject[2] = Integer.toString(projectList.get(i).getParentProjectID());
            } else {
                currentProject[2] = Integer.toString(projectList.get(i).getId());
            }
            //start date
            currentProject[3] =projectList.get(i).getStartDate().getYear() +
                    "," + (projectList.get(i).getStartDate().getMonthValue() - 1) + "," +
                    projectList.get(i).getStartDate().getDayOfMonth();
            //deadline
            currentProject[4] = projectList.get(i).getDeadline().getYear() +
                    "," + (projectList.get(i).getDeadline().getMonthValue() - 1) + "," +
                    projectList.get(i).getDeadline().getDayOfMonth();
            //duration
            if((projectList.get(i).getEstTimeHours() / 24) < 1){
                currentProject[5] = Integer.toString(1);
            } else{
                currentProject[5] = Integer.toString((projectList.get(i).getEstTimeHours() / 24));
            }
            //percentage complete
            if(projectList.get(i).getSpentTimeHours() == 0){
                currentProject[6] = Integer.toString(0);
            }
            else if((projectList.get(i).getSpentTimeHours() / (double) projectList.get(i).getEstTimeHours()) * 100 > 100){
                currentProject[6] = Integer.toString(100);
            } else {
                currentProject[6] = Double.toString((projectList.get(i).getSpentTimeHours() / (double)projectList.get(i).getEstTimeHours()) * 100);
            }
            //Dependencies
            String dependencies = parentId(projectList.get(i),projectList);
            if(dependencies.length() == 0){
                currentProject[7] = null;
            } else{
                currentProject[7] = null;
            }
            dataArrays[i] = currentProject;
        }

        return dataArrays;
    }

    public String[][] taskGanttChart(List<Task> taskList){
        String[][] dataArrays = new String[taskList.size()][];

        for (int i = 0; i < taskList.size() ; i++){
            String[] currentTask = new String[8];
            //TaskID
            currentTask[0] = Integer.toString(taskList.get(i).getId());
            //Task Title
            currentTask[1] = taskList.get(i).getTitle();
            //Project id or parentId id
            currentTask[2] = Integer.toString(taskList.get(i).getProjectID());
            // start date
            currentTask[3] = taskList.get(i).getStartDate().getYear() +
                    "," + (taskList.get(i).getStartDate().getMonthValue() - 1) + "," +
                    taskList.get(i).getStartDate().getDayOfMonth();
            // deadline
            currentTask[4] = taskList.get(i).getDeadline().getYear() +
                    "," + (taskList.get(i).getDeadline().getMonthValue() - 1) + "," +
                    taskList.get(i).getDeadline().getDayOfMonth();

            // duration
            if((taskList.get(i).getEstTime() / 24) < 1){
                currentTask[5] = Integer.toString(1);
            } else{
                currentTask[5] = Integer.toString((taskList.get(i).getEstTime() / 24));
            }
            //percentage complete
            if(taskList.get(i).getSpentTime() == 0){
                currentTask[6] = Integer.toString(0);
            }

            else if(( taskList.get(i).getSpentTime() / (double) taskList.get(i).getEstTime()) * 100 > 100){
                currentTask[6] = Integer.toString(100);
            } else {
                currentTask[6] = Double.toString(((taskList.get(i).getSpentTime() / (double) taskList.get(i).getEstTime())*100));
            }
            //dependencies
            String dependencies = parentId(taskList.get(i),taskList);
            if(dependencies.length() == 0){
                currentTask[7] = null;
            } else{
                currentTask[7] = dependencies;
            }

            dataArrays[i] = currentTask;
        }

        return dataArrays;
    }

    private String parentId(Project task, List<Project> taskList){
        String dependencies = "";

        for(Project currentTask : taskList ){
            if(currentTask.getParentProjectID() == task.getId()){
                dependencies += currentTask.getId() + ",";
            }
        }
        if(dependencies.length() == 0){
            return dependencies = "";
        }

        return dependencies.substring(0,dependencies.length()-1);
    }
    private String parentId(Task task, List<Task> taskList){
        String dependencies = "";

            for(Task currentTask : taskList ){
                if(currentTask.getParentTaskID() == task.getId()){
                    dependencies += currentTask.getId() + ",";
                }
            }
            if(dependencies.length() == 0){
                return dependencies = "";
            }

        return dependencies.substring(0,dependencies.length()-1);
    }
}
