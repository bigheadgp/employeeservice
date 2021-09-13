package com.example.employee.dao.mapper;

import com.example.employee.dao.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface EmployeeMapper {

    List<Employee> selectEmployeeByBirthdateBetween(Date from, Date to);

}
