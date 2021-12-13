package com.fourthgroup.projectmanageman.utility;

import java.sql.Connection;

/*
    ===============================
    Author: Simon Roswall Jørgensen
    Date: Nov 27, 2021
    ===============================
 */

public interface ConnectionPool {

    Connection getConnection();
    boolean releaseConnection(Connection connection);
}
