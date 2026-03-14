package com.sba301.backendpe.service;

import com.sba301.backendpe.entity.Student;
import com.sba301.backendpe.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public Student create(Student student) {
        validate(student, null);
        return studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public List<String> getMajors() {
        return List.of("Software Engineering", "Business", "Design", "AI", "Finance");
    }

    private void validate(Student student, Long editingId) {
        if (student.getStudentName() == null || student.getStudentName().trim().isEmpty()) {
            throw new RuntimeException("Student name is required");
        }
        if (studentRepository.existsByStudentName(student.getStudentName())) {
            if (editingId == null || !studentRepository.findById(editingId)
                    .map(s -> s.getStudentName().equals(student.getStudentName()))
                    .orElse(false)) {
                throw new RuntimeException("Duplicate student name");
            }
        }
        if (student.getBirthDate() == null || student.getBirthDate().trim().isEmpty()) {
            throw new RuntimeException("Birth date is required");
        }
        if (student.getGender() == null || student.getGender().trim().isEmpty()) {
            throw new RuntimeException("Gender is required");
        }
        if (student.getMajor() == null || student.getMajor().trim().isEmpty()) {
            throw new RuntimeException("Major is required");
        }
        if (student.getAvatarUrl() == null || student.getAvatarUrl().trim().isEmpty()) {
            throw new RuntimeException("Avatar URL is required");
        }
    }
}
