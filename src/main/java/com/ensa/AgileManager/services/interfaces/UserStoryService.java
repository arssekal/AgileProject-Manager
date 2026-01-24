package com.ensa.AgileManager.services.interfaces;

import com.ensa.AgileManager.dtos.TaskDto;
import com.ensa.AgileManager.dtos.UserStoryDto;
import com.ensa.AgileManager.entities.UserStory;
import com.ensa.AgileManager.enums.Status;

import java.util.List;

public interface UserStoryService {
    UserStoryDto getUserStory(Long userStoryId);

    UserStoryDto updateUserStory(Long userStoryId, UserStoryDto userStoryDto);

    TaskDto createTask(Long userStoryId, TaskDto taskDto);

    List<TaskDto> getUserStoryTasks(Long userStoryId);

    UserStoryDto deleteUseStoryWithTasks(Long userStoryId);

    UserStoryDto changeUserStoryStatus(Long id, Status status);
}
