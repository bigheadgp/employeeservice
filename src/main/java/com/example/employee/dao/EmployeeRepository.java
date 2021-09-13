package com.example.employee.dao;

import com.example.employee.dao.mapper.MybatisEmployeeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


/**
 * @Author: pguo
 * @Date: 2021/9/8 20:54
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, MybatisEmployeeRepository {

    public Employee findByName(String name);

    List<Employee> findEmployeeByBirthdayBetween(Date from, Date to);

}
