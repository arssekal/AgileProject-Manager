package com.ensa.AgileManager.entities;

import com.ensa.AgileManager.enums.Priority;
import com.ensa.AgileManager.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_story")
@Builder
public class UserStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 30, message = "la longueur de titre du userStory dois etre entre 3 et 30")
    private  String titre;
    // En tant que [type d’utilisateur], je veux [fonctionnalité], afin de [bénéfice].
    private String description;
    @NotNull(message = "la priority ne peut pas etre null")
    private Priority priority;
    // @ColumnDefault("TO_DO")
    @NotNull(message = "le status ne peut pas etre null")
    private Status statut;
    private String critereAcceptation;
    @ManyToOne
    @JoinColumn(name = "epic_id")
    private Epic epic;

    @OneToMany(mappedBy = "userStory", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "sprintBacklog_id")
    private SprintBacklog sprintBacklog;
}
