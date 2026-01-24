package com.ensa.AgileManager.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SprintDto {
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
}
