package com.arssekal.AgileManager.services.interfaces;

import com.arssekal.AgileManager.dtos.SprintBacklogDto;
import com.arssekal.AgileManager.dtos.UserStoryDto;

import java.util.List;

public interface SprintBacklogService {
    SprintBacklogDto createSprint(Long projectId, SprintBacklogDto sprintBacklogDto);

    SprintBacklogDto getSprintDetails(Long id);

    List<Long> addUserStoryToSprint(Long sprintBacklogId, List<Long> storiesId);

    List<UserStoryDto> getSprintUserStories(Long id);

    UserStoryDto removeUserStory(Long sprintBacklogId, Long storyId);

    List<SprintBacklogDto> getActiveSprints();
}
