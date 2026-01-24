package com.ensa.AgileManager.services.interfaces;

import com.ensa.AgileManager.dtos.SprintBacklogDto;
import com.ensa.AgileManager.dtos.UserStoryDto;

import java.util.List;

public interface SprintBacklogService {
    SprintBacklogDto createSprint(Long projectId, SprintBacklogDto sprintBacklogDto);

    SprintBacklogDto getSprintDetails(Long id);

    UserStoryDto addUserStoryToSprint(Long sprintBacklogId, Long storyId);

    List<UserStoryDto> getSprintUserStories(Long id);

    UserStoryDto removeUserStory(Long sprintBacklogId, Long storyId);
}
