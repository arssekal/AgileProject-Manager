package com.arssekal.AgileManager.entities;

import com.arssekal.AgileManager.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 30 , message = "la longueur de titre du tache dois etre entre 3 et 50")
    private String titre;
    private String description;
//    @ColumnDefault("TO_DO")
    @NotNull
    private Status statut;
    @ManyToOne
    @JoinColumn(name = "userStory_id")
    private UserStory userStory;
    @OneToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;
}
