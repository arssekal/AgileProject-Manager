package com.ensa.AgileManager.dtos;
import com.ensa.AgileManager.enums.Priority;
import com.ensa.AgileManager.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserStoryDto {
    private Long id;
    private String   titre;
    private String   description;
    private Priority priority;
    private Status   statut;
    private String   critereAcceptation;
}
