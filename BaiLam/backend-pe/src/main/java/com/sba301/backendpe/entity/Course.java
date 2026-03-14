package com.sba301.backendpe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    private String courseName;

    @Column(nullable = false)
    private int credits;

    @Column(nullable = false, length = 120)
    private String instructor;

    @Column(nullable = false)
    private String level;

    @Column(nullable = false)
    private boolean active;
}
