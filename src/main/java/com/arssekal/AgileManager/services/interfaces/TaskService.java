package com.arssekal.AgileManager.services.interfaces;

import com.arssekal.AgileManager.dtos.TaskDto;
import com.arssekal.AgileManager.enums.Status;

import java.util.List;

public interface TaskService {
    TaskDto getTask(Long id);

    TaskDto updateTask(Long id, TaskDto taskDto);

    TaskDto deleteTask(Long id);

    TaskDto updateTaskStatus(Long id, Status taskStatus);

    List<TaskDto> getAllTasks();
}
