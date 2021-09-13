package com.example.employee.dao.mapper;

import com.example.employee.dao.Employee;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MybatisEmployeeRepository {

    List<Employee> findEmployeeByBirthdayBetween(Date from, Date to);

}
