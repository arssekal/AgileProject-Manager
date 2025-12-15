package com.arssekal.AgileManager.services.interfaces;

import com.arssekal.AgileManager.dtos.SprintBacklogDto;
import com.arssekal.AgileManager.dtos.UserStoryDto;

import java.util.List;

public interface SprintBacklogService {
    SprintBacklogDto createSprint(SprintBacklogDto sprintBacklogDto);

    SprintBacklogDto getSprintDetails(Long id);

    UserStoryDto addUserStoryToSprint(Long sprintBacklogId, Long storyId);

    List<UserStoryDto> getSprintUserStories(Long id);

    UserStoryDto removeUserStory(Long sprintBacklogId, Long storyId);
}
