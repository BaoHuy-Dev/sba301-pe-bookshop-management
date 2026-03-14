package com.sba301.backendpe.repository;

import com.sba301.backendpe.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByFullName(String fullName);
}
