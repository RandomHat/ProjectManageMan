package com.fourthgroup.projectmanageman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ProjectManageManApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManageManApplication.class, args);
    }

}
