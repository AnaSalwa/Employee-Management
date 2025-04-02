package com.example.employeemanagement;

import com.example.employeemanagement.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void addEmployee() {
        employeeService.addEmployee(1 , "John Doe", 5000.0);
        assertNotNull(employeeService.getSalary(1));
    }

    @Test
    void getSalary() {
        employeeService.addEmployee(1, "John Doe", 5000.0);
        assertEquals(5000.0, employeeService.getSalary(1));
    }

    @Test
    void getSalaryAfterDiscount() {
        employeeService.addEmployee(1, "John Doe", 5000.0);
        assertEquals(4500.0, employeeService.getSalaryAfterDiscount(1, 10.0));
    }

    @Test
    void loopSalary() {
        employeeService.addEmployee(1, "John Doe", 5000.0);
        employeeService.addEmployee(2, "Jane Doe", 6000.0);
        String result = employeeService.loopSalary(1, 2);
        assertTrue(result.contains("Emp 1: 5000.0 | "));
        assertTrue(result.contains("Emp 2: 6000.0 | "));
    }

    /*@Test
    void calculateSalaryWithBonusAndDiscount() {
        employeeService.addEmployee(1, "John Doe", 5000.0);
        assertEquals(5450.0, employeeService.calculateSalaryWithBonusAndDiscount(1, 10.0, 10.0));
    }*/

    @Test
    void calculateSalaryWithBonusAndDiscount() {
        // Ajouter un employé avec un salaire de 5000.0
        employeeService.addEmployee(1, "John Doe", 5000.0);

        // Vérifier que le calcul est correct
        assertEquals(4998.0, employeeService.calculateSalaryWithBonusAndDiscount(1, 2.0, 5.0));
    }

    @Test
    void calculateSalaryWithBonusAndDiscount_NullEmployee() {
        // Vérifier que la méthode retourne null si l'employé n'existe pas
        assertNull(employeeService.calculateSalaryWithBonusAndDiscount(2, 10.0, 10.0));
    }
}
