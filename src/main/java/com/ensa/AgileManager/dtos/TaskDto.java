package com.ensa.AgileManager.dtos;
import com.ensa.AgileManager.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TaskDto {
    private Long id;
    private String titre;
    private String description;
    private Status statut;
}
