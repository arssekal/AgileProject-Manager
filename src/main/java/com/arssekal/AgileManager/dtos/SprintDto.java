package com.arssekal.AgileManager.dtos;

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
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
}
