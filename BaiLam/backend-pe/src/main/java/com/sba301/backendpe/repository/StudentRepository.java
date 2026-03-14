package com.sba301.backendpe.repository;

import com.sba301.backendpe.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByStudentName(String studentName);
}
