package com.sba301.backendpe.config;

import com.sba301.backendpe.entity.*;
import com.sba301.backendpe.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(
            EmployeeRepository employeeRepository,
            ProductRepository productRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            EventRepository eventRepository
    ) {
        return args -> {
            if (employeeRepository.count() == 0) {
                employeeRepository.save(Employee.builder().fullName("Nguyen Van A").age(24).email("a@company.com").department("IT").build());
                employeeRepository.save(Employee.builder().fullName("Tran Thi B").age(31).email("b@company.com").department("HR").build());
            }

            if (productRepository.count() == 0) {
                productRepository.save(Product.builder().productName("Mechanical Keyboard").category("Electronics").price(79.99).inStock(true).description("Hot swap keyboard").build());
                productRepository.save(Product.builder().productName("Java Handbook").category("Books").price(19.5).inStock(true).description("Backend practice guide").build());
            }

            if (studentRepository.count() == 0) {
                studentRepository.save(Student.builder().studentName("Le Minh Khang").birthDate("2004-09-12").gender("Male").major("Software Engineering").avatarUrl("https://images.unsplash.com/photo-1500648767791-00dcc994a43e").build());
                studentRepository.save(Student.builder().studentName("Pham Ngoc Anh").birthDate("2005-01-20").gender("Female").major("Business").avatarUrl("https://images.unsplash.com/photo-1494790108377-be9c29b29330").build());
            }

            if (courseRepository.count() == 0) {
                courseRepository.save(Course.builder().courseName("React Fundamentals").credits(3).instructor("Mr. Long").level("Beginner").active(true).build());
                courseRepository.save(Course.builder().courseName("Spring Boot API").credits(4).instructor("Ms. Hoa").level("Intermediate").active(true).build());
            }

            if (eventRepository.count() == 0) {
                eventRepository.save(Event.builder()
                        .title("Frontend Bootcamp")
                        .description("Hands-on React and UI workshop")
                        .category("Tech")
                        .eventDate(LocalDate.now().plusDays(7).toString())
                        .seats(120)
                        .online(true)
                        .level("Beginner")
                        .bannerUrl("https://images.unsplash.com/photo-1511578314322-379afb476865")
                        .build());

                eventRepository.save(Event.builder()
                        .title("Product Strategy Meetup")
                        .description("Case study and roadmap planning")
                        .category("Business")
                        .eventDate(LocalDate.now().plusDays(14).toString())
                        .seats(80)
                        .online(false)
                        .level("Intermediate")
                        .bannerUrl("https://images.unsplash.com/photo-1515169067868-5387ec356754")
                        .build());
            }
        };
    }
}
