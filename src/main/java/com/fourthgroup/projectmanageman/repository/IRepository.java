package com.fourthgroup.projectmanageman.repository;

/*
    ===============================
    Author: Simon Roswall Jørgensen
    Date: Dec 11, 2021
    ===============================
 */

public interface IRepository<T> {

    int write(T t);
    T read(int id);
    boolean update(T t);
    boolean delete(T t);
}
