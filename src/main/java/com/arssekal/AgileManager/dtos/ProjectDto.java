package com.arssekal.AgileManager.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProjectDto {
    private String nom;
    private String description;
    private Long productOwnerId;
    private ProductBacklogDto productBacklogData;
}
