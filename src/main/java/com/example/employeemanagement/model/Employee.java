package com.example.employeemanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Employee.java
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private Double salary;
}