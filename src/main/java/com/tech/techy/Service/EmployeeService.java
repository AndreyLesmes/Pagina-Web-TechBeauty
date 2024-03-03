package com.tech.techy.Service;

import java.util.List;

import com.tech.techy.Entity.Employee;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int pkId);

    void create(Employee employee);

    void update(Employee employee);

    void delete(Employee employee);
}
