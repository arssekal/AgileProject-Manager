package com.ensa.AgileManager.services.implementations;

import com.ensa.AgileManager.dtos.TaskDto;
import com.ensa.AgileManager.entities.Task;
import com.ensa.AgileManager.enums.Status;
import com.ensa.AgileManager.exceptions.TaskNotFoundException;
import com.ensa.AgileManager.mappers.TaskMapper;
import com.ensa.AgileManager.repositories.TaskRepository;
import com.ensa.AgileManager.services.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public TaskDto getTask(Long id) {
        Task task = task(id);
        return TaskMapper.INSTANCE.toDto(task);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task task = task(id);
        task.setTitre(taskDto.getTitre());
        task.setDescription(taskDto.getDescription());
        task.setStatut(taskDto.getStatut());
        Task savedTask = taskRepository.save(task);
        return TaskMapper.INSTANCE.toDto(savedTask);
    }

    @Override
    public TaskDto deleteTask(Long id) {
        Task task = task(id);
        taskRepository.delete(task);
        return TaskMapper.INSTANCE.toDto(task);
    }

    @Override
    public TaskDto updateTaskStatus(Long id, Status taskStatus) {
        Task task = task(id);
        task.setStatut(taskStatus);
        Task savedTask = taskRepository.save(task);
        return TaskMapper.INSTANCE.toDto(savedTask);
    }

    @Override
    public Page<TaskDto> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable)
                .map(TaskMapper.INSTANCE::toDto);
    }

    private Task task(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }
}
