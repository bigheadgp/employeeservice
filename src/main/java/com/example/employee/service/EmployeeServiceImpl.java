package com.example.employee.service;

import com.example.employee.dao.Employee;
import com.example.employee.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: pguo
 * @Date: 2021/9/9 12:13
 */
@Service
public class EmployeeServiceImpl implements  EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.isPresent()?employeeOptional.get():null;
    }

    @Override
    public Employee getEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public boolean exists(String name) {
        return employeeRepository.findByName(name)!=null;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
}
