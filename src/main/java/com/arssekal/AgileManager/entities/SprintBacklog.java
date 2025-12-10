package com.arssekal.AgileManager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SprintBacklog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @OneToMany(mappedBy = "sprintBacklog", cascade = CascadeType.ALL)
    private List<UserStory> UserStories;
    @OneToOne(mappedBy = "sprintBacklog", cascade = CascadeType.ALL)
    private Sprint sprint;
}
