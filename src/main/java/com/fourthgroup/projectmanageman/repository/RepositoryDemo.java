package com.fourthgroup.projectmanageman.repository;

import com.fourthgroup.projectmanageman.utility.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryDemo {

    @Autowired
    ConnectionPool connectionPool;
}
