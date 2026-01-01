package com.arssekal.AgileManager.dtos;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SprintBacklogDto {
    private Long id;
    private String nom;
    private String description;
    private Long scrumMasterId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
}
