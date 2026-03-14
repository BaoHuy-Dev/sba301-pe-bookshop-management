package com.sba301.backendpe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String eventDate;

    @Column(nullable = false)
    private int seats;

    @Column(nullable = false)
    private boolean online;

    @Column(nullable = false)
    private String level;

    @Column(nullable = false, length = 255)
    private String bannerUrl;
}
