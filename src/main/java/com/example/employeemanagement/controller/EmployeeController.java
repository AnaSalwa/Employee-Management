package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import org.springframework.web.bind.annotation.*;
import com.example.employeemanagement.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

// EmployeeController.java

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/employees")
    public ResponseEntity<Iterable<Employee>> getEmployees() {
        Iterable<Employee> employees = employeeService.getEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer empId) {
        Employee employee = employeeService.getEmployeeById(empId);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/addemployee")
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee.getEmpId(), employee.getName(), employee.getSalary());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{empId}/salary")
    public ResponseEntity<Double> getSalary(@PathVariable Integer empId) {
        Double salary = employeeService.getSalary(empId);
        return ResponseEntity.ok(salary);
    }

    @GetMapping("/{empId}/name")
    public ResponseEntity<String> getNameEmployee(@PathVariable Integer empId){
        String name = employeeService.getNameEmployee(empId);
        return ResponseEntity.ok(name);
    }

    @GetMapping("/{empId}/salary-after-discount/{discountPercentage}")
    public ResponseEntity<Double> getSalaryAfterDiscount(@PathVariable Integer empId, @PathVariable Double discountPercentage) {
        Double salary = employeeService.getSalaryAfterDiscount(empId, discountPercentage);
        return ResponseEntity.ok(salary);
    }

    @GetMapping("/loop-salary/{startId}/{endId}")
    public ResponseEntity<String> loopSalary(@PathVariable Integer startId, @PathVariable Integer endId){
        String salaries = employeeService.loopSalary(startId, endId);
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/{empId}/salary-with-bonus-and-discount/{bonusPercentage}/{discountPercentage}")
    public ResponseEntity<Double> calculateSalaryWithBonusAndDiscount(@PathVariable Integer empId, @PathVariable Double bonusPercentage, @PathVariable Double discountPercentage) {
        Double salary = employeeService.calculateSalaryWithBonusAndDiscount(empId, bonusPercentage, discountPercentage);
        return ResponseEntity.ok(salary);
    }
}