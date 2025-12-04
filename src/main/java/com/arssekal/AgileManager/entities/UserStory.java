package com.arssekal.AgileManager.entities;

import com.arssekal.AgileManager.enums.Priority;
import com.arssekal.AgileManager.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private  String titre;
    // En tant que [type d’utilisateur], je veux [fonctionnalité], afin de [bénéfice].
    private String description;
    private Priority priority;
    private Status statut;
    private String critereAcceptation;
    private Epic epic;
    private SprintBacklog sprintBacklog;
}
