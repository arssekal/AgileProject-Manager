package com.arssekal.AgileManager.services.interfaces;

import com.arssekal.AgileManager.dtos.TaskDto;

public interface TaskService {
    TaskDto getTask(Long id);

    TaskDto updateTask(Long id, TaskDto taskDto);

    TaskDto deleteTask(Long id);
}
