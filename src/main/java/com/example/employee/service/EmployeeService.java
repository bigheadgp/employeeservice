package com.example.employee.service;

import com.example.employee.dao.Employee;

import java.util.Date;
import java.util.List;

/**
 * @Author: pguo
 * @Date: 2021/9/9 11:07
 */
public interface EmployeeService {

    public Employee getEmployeeById(Long id);

    public Employee getEmployeeByName(String name);

    public List<Employee> getAllEmployees();

    public boolean exists(String name);

    public Employee save(Employee employee);

    public List<Employee> searchEmployeesByBirthdayBetween(Date from, Date to);

}
