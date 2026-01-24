package com.ensa.AgileManager.dtos;

import com.ensa.AgileManager.enums.OtherStatus;
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
