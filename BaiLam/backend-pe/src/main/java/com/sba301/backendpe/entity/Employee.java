package com.sba301.backendpe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80, unique = true)
    private String fullName;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    private String department;
}
