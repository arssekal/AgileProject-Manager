package com.arssekal.AgileManager.dtos;
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
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private List<UserStoryDto> UserStories;
    private List<TaskDto> tasks;
}
