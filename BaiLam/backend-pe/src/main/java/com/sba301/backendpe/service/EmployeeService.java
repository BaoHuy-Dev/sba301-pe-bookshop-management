package com.sba301.backendpe.service;

import com.sba301.backendpe.entity.Employee;
import com.sba301.backendpe.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    public Employee create(Employee employee) {
        validate(employee, null);
        return employeeRepository.save(employee);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<String> getDepartments() {
        return List.of("IT", "HR", "Finance", "Marketing", "Operations");
    }

    private void validate(Employee employee, Long editingId) {
        if (employee.getFullName() == null || employee.getFullName().trim().isEmpty()) {
            throw new RuntimeException("Full name is required");
        }
        if (employee.getFullName().length() > 80) {
            throw new RuntimeException("Full name max length is 80");
        }
        if (employeeRepository.existsByFullName(employee.getFullName())) {
            if (editingId == null || !employeeRepository.findById(editingId)
                    .map(e -> e.getFullName().equals(employee.getFullName()))
                    .orElse(false)) {
                throw new RuntimeException("Duplicate full name");
            }
        }
        if (employee.getAge() <= 18 || employee.getAge() >= 65) {
            throw new RuntimeException("Age must be > 18 and < 65");
        }
        if (employee.getEmail() == null || employee.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email is required");
        }
        if (employee.getEmail().length() > 150) {
            throw new RuntimeException("Email max length is 150");
        }
        if (employee.getDepartment() == null || employee.getDepartment().trim().isEmpty()) {
            throw new RuntimeException("Department is required");
        }
    }
}
