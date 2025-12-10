package com.arssekal.AgileManager.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    @OneToOne
    @JoinColumn(name = "sprintBacklog_id")
    private SprintBacklog sprintBacklog;
    @OneToOne
    @JoinColumn(name = "scrumMaster_id")
    private ScrumMaster scrumMaster;
}
