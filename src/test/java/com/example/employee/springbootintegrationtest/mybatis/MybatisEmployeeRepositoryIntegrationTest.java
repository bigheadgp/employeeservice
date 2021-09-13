package com.example.employee.springbootintegrationtest.mybatis;

import com.example.employee.dao.Employee;
import com.example.employee.dao.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureMybatis
@ActiveProfiles("test")
public class MybatisEmployeeRepositoryIntegrationTest {

    @Autowired
    EmployeeRepository employeeRepository;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private List<Employee> allEmployees = null ;

    @Before
    public void setup() {

        Date date1;
        Date date2;
        Date date3;

        try {
            date1 = dateFormat.parse("2010-07-18");
            date2 = dateFormat.parse("2012-09-21");
            date3 = dateFormat.parse("2019-12-11");
        } catch (ParseException e) {
            throw new RuntimeException("fail to parse date string", e);
        }

        Employee johnson = new Employee("Johnson",date1,"johnson@gmail.com");
        Employee tommy = new Employee("Tommy",date2,"tommy@gmail.com");
        Employee rose = new Employee("Rose",date3,"rose@gmail.com");

        allEmployees = Arrays.asList(johnson, tommy, rose);
    }


    @Test
    public void whenBirthOk_thenReturn2Employees() {

        Date from;
        Date to;

        try {
            from = dateFormat.parse("2010-01-01");
            to = dateFormat.parse("2012-12-30");
        } catch (ParseException e) {
            throw new RuntimeException("fail to parse date string", e);
        }

        employeeRepository.saveAllAndFlush(allEmployees);

        List<Employee> searchResults = employeeRepository.findEmployeeByBirthdayBetween(from, to);
        Employee johnson = allEmployees.get(0);
        Employee tommy = allEmployees.get(1);
        Employee rose = allEmployees.get(2);

        assertThat(searchResults).hasSize(2);
        assertThat(searchResults).extracting(Employee::getName).contains(johnson.getName(), tommy.getName()).doesNotContain(rose.getName());

        employeeRepository.deleteAll(allEmployees);
    }

}
