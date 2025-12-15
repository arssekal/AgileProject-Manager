package com.arssekal.AgileManager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 3, max = 50, message = "la longueur de nom du sprintBacklog dois etre entre 3 et 50")
    private String nom;
    @OneToMany(mappedBy = "sprintBacklog")
    private List<UserStory> UserStories;
    @OneToOne(mappedBy = "sprintBacklog")
    private Sprint sprint;
}
