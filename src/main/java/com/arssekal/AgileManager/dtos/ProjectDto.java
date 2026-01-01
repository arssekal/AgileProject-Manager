package com.arssekal.AgileManager.dtos;

import com.arssekal.AgileManager.enums.OtherStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProjectDto {
    private Long id;
    private String nom;
    private String description;
    private OtherStatus status;
    private LocalDate createdAt;
    private Long productOwnerId;
    private ProductBacklogDto productBacklogData;
}
