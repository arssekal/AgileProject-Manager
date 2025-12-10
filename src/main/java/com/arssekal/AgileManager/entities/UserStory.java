package com.arssekal.AgileManager.entities;

import com.arssekal.AgileManager.enums.Priority;
import com.arssekal.AgileManager.enums.Status;
import jakarta.persistence.*;
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
    private  String titre;
    // En tant que [type d’utilisateur], je veux [fonctionnalité], afin de [bénéfice].
    private String description;
    private Priority priority;
//    @ColumnDefault("TO_DO")
    private Status statut;
    private String critereAcceptation;
    @ManyToOne
    @JoinColumn(name = "epic_id")
    private Epic epic;

    @OneToMany(mappedBy = "userStory", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "sprintBacklog_id")
    private SprintBacklog sprintBacklog;
}
