package com.arssekal.AgileManager.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EpicDto {
    private Long id;
    private String titre;
    private String description;
    private List<UserStoryDto> userStories;
}
