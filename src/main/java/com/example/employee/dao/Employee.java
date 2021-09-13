package com.example.employee.dao;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Author: pguo
 * @Date: 2021/9/8 20:17
 */
@Entity
@Table(name = "employees")
public class Employee {
    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, Date birthday, String email) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 40)
    private String name;

    @Column(name="birth_date")
    private Date birthday;

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
