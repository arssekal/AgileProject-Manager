package com.ensa.AgileManager.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ensa.AgileManager.enums.OtherStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
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
    @FutureOrPresent
    private LocalDate dateDebut;
    @Future
    private LocalDate dateFin;
    @OneToOne
    @JoinColumn(name = "sprintBacklog_id")
    private SprintBacklog sprintBacklog;
    @ManyToOne
    @JoinColumn(name = "scrumMaster_id")
    private ScrumMaster scrumMaster;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
