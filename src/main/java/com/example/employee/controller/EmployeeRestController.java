package com.example.employee.controller;

import com.example.employee.dao.Employee;
import com.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: pguo
 * @Date: 2021/9/9 11:08
 */
@RestController
@RequestMapping("/api")
@EnableAutoConfiguration
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        HttpStatus status = HttpStatus.CREATED;
        Employee saved = employeeService.save(employee);
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


    @GetMapping("/searchEmployees")
    public List<Employee> searchEmployeesByBirthday(@RequestParam Date from, @RequestParam Date to) {

        return employeeService.searchEmployeesByBirthdayBetween(from, to);
    }
}
