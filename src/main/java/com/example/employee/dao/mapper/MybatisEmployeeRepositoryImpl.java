package com.example.employee.dao.mapper;

import com.example.employee.dao.Employee;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

//@Repository
public class MybatisEmployeeRepositoryImpl implements MybatisEmployeeRepository{

    @Resource
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> findEmployeeByBirthdayBetween(Date from, Date to) {
        return employeeMapper.selectEmployeeByBirthdateBetween(from,to);
    }

}
