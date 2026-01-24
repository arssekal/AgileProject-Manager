package com.ensa.AgileManager.dtos;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductBacklogDto {
    private Long id;
    private String nom;
    private String description;
}
