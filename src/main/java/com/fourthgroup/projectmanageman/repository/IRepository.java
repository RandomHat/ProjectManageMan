package com.fourthgroup.projectmanageman.repository;

import com.fourthgroup.projectmanageman.utility.ConnectionPool;

public interface IRepository<T> {

    int write(T t);
    T read(int id);
    boolean update(T t);
    boolean delete(T t);
}
