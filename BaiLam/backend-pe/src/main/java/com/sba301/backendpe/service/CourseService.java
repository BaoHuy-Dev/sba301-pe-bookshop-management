package com.sba301.backendpe.service;

import com.sba301.backendpe.entity.Course;
import com.sba301.backendpe.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    public Course create(Course course) {
        validate(course, null);
        return courseRepository.save(course);
    }

    public Course update(Long id, Course request) {
        Course old = getById(id);
        old.setCourseName(request.getCourseName());
        old.setCredits(request.getCredits());
        old.setInstructor(request.getInstructor());
        old.setLevel(request.getLevel());
        old.setActive(request.isActive());
        validate(old, id);
        return courseRepository.save(old);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    public List<String> getLevels() {
        return List.of("Beginner", "Intermediate", "Advanced");
    }

    private void validate(Course course, Long editingId) {
        if (course.getCourseName() == null || course.getCourseName().trim().isEmpty()) {
            throw new RuntimeException("Course name is required");
        }
        if (courseRepository.existsByCourseName(course.getCourseName())) {
            if (editingId == null || !courseRepository.findById(editingId)
                    .map(c -> c.getCourseName().equals(course.getCourseName()))
                    .orElse(false)) {
                throw new RuntimeException("Duplicate course name");
            }
        }
        if (course.getCredits() <= 0 || course.getCredits() > 10) {
            throw new RuntimeException("Credits must be > 0 and <= 10");
        }
        if (course.getInstructor() == null || course.getInstructor().trim().isEmpty()) {
            throw new RuntimeException("Instructor is required");
        }
        if (course.getLevel() == null || course.getLevel().trim().isEmpty()) {
            throw new RuntimeException("Level is required");
        }
    }
}
