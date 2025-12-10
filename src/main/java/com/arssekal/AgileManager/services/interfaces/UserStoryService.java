package com.arssekal.AgileManager.services.interfaces;

import com.arssekal.AgileManager.dtos.TaskDto;
import com.arssekal.AgileManager.dtos.UserStoryDto;

import java.util.List;

public interface UserStoryService {
    UserStoryDto getUserStory(Long userStoryId);

    UserStoryDto updateUserStory(Long userStoryId, UserStoryDto userStoryDto);

    TaskDto createTask(Long userStoryId, TaskDto taskDto);

    List<TaskDto> getUserStoryTasks(Long userStoryId);

    UserStoryDto deleteUseStoryWithTasks(Long userStoryId);
}
