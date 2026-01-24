package com.ensa.AgileManager.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EpicWithStoriesDto {
    private Long id;
    private String titre;
    private String description;
    private List<UserStoryDto> userStories;
}
