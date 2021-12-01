package controller;

import com.fourthgroup.projectmanageman.model.Project;
import com.fourthgroup.projectmanageman.repository.ProjectRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import service.ProjectService;

import javax.servlet.http.HttpSession;

public class ProjectController {

    private ProjectService projectService = new ProjectService();

    @GetMapping("/create/create-project")
    public String createProjectGet(HttpSession session){
        //Create project form
        return "create-project";
    }

    @PostMapping("/create/create-project") // Send form
    public String createProjectPost(@ModelAttribute Project project, //Forminput skal sanitizes før det gemmes
                                    HttpSession session){
        //projectService.parseInputDate("YYYY-MM-DD"); //Parse inputdate string fra form (model- eller service-lag?)

        int projectID = projectService.writeNewProject(project);

        //ProjectID = 0 hvis query fejler
        //Check id og pipe videre

        return "project-create-success";
    }
}
