package com.ensa.AgileManager.services.interfaces;

import com.ensa.AgileManager.dtos.TaskDto;
import com.ensa.AgileManager.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    TaskDto getTask(Long id);

    TaskDto updateTask(Long id, TaskDto taskDto);

    TaskDto deleteTask(Long id);

    TaskDto updateTaskStatus(Long id, Status taskStatus);

    Page<TaskDto> getAllTasks(Pageable pageable);
}
