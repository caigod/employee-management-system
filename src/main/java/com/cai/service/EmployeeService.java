package com.cai.service;

import com.cai.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    void save(Employee employee);
    List<Employee> getAll();
    void delEmp(Integer id);
    Employee findById(Integer id);
    void updateEmp(Employee employee);
    Employee findByName(String lastname);
}
