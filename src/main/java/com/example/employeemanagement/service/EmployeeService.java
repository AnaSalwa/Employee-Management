package service;

import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Adds a new employee to the database.
     * @param empId The employee ID.
     * @param name The employee name.
     * @param salary The employee salary.
     */
    public void addEmployee(Integer empId, String name, Double salary) {
        try {
            Employee employee = new Employee();
            employee.setEmpId(empId);
            employee.setName(name);
            employee.setSalary(salary);
            employeeRepository.save(employee);

        } catch (Exception e) {
            throw new RuntimeException("Error adding employee: " + e.getMessage());
        }
    }


    /**
     * Retrieves the salary of an employee.
     * @param empId The employee ID.
     * @return The employee's salary, or null if the employee is not found.
     */
    public Double getSalary(Integer empId) {
        try {
            Employee employee = employeeRepository.findById(empId).orElse(null);
            return employee != null ? employee.getSalary() : null;
        } catch (Exception e) {
            throw new RuntimeException("Error getting salary: " + e.getMessage());
        }

    }

    /**
     * Calculates the salary after a discount.
     * @param empId The employee ID.
     * @param discountPercentage The discount percentage.
     * @return The discounted salary, or null if the employee is not found.
     */
    public Double getSalaryAfterDiscount(Integer empId, Double discountPercentage) {
        try {
            Double salary = getSalary(empId);
            if (salary != null) {
                return salary - (salary * discountPercentage / 100);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error calculating discounted salary: " + e.getMessage());
        }
    }

    /**
     * Retrieves salaries for a range of employee IDs.
     * @param startId The starting employee ID.
     * @param endId The ending employee ID.
     * @return A string containing the salaries of employees within the specified range.  Returns an error message if any issues occur.
     */
    public String loopSalary(Integer startId, Integer endId) {
        try {
            StringBuilder result = new StringBuilder("Salaries of employees: ");
            for (int i = startId; i <= endId; i++) {
                Employee employee = employeeRepository.findById(i).orElse(null);
                if (employee != null) {
                    result.append("Emp ").append(i).append(": ").append(employee.getSalary()).append(" | ");
                } else {
                    result.append("Emp ").append(i).append(": No employee found. | ");
                }
            }
            return result.toString();
        } catch (Exception e) {
            return "An error occurred.";
        }
    }

    /**
     * Calculates the salary with bonus and discount.
     * @param empId The employee ID.
     * @param bonusPercentage The bonus percentage.
     * @param discountPercentage The discount percentage.
     * @return The final salary after bonus and discount, or null if the employee is not found.
     */
    public Double calculateSalaryWithBonusAndDiscount(Integer empId, Double bonusPercentage, Double discountPercentage) {
        try {
            Double salary = getSalary(empId);
            if (salary != null) {
                Double bonus = salary * bonusPercentage / 100;
                Double finalSalary = salary + bonus;
                Double discount = finalSalary * discountPercentage / 100;
                return finalSalary - discount;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error calculating salary with bonus and discount: " + e.getMessage());
        }
    }
}