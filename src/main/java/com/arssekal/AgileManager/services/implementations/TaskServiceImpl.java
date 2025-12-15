package com.arssekal.AgileManager.services.implementations;

import com.arssekal.AgileManager.dtos.TaskDto;
import com.arssekal.AgileManager.entities.Task;
import com.arssekal.AgileManager.enums.Status;
import com.arssekal.AgileManager.exceptions.TaskNotFoundException;
import com.arssekal.AgileManager.mappers.Mapper;
import com.arssekal.AgileManager.repositories.TaskRepository;
import com.arssekal.AgileManager.services.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskDto getTask(Long id) {
        Task task = task(id);
        return Mapper.mapToTaskDto(task);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task task = task(id);
        task.setTitre(taskDto.getTitre());
        task.setDescription(taskDto.getDescription());
        task.setStatut(taskDto.getStatut());
        Task savedTask = taskRepository.save(task);
        return Mapper.mapToTaskDto(savedTask);
    }

    @Override
    public TaskDto deleteTask(Long id) {
        Task task = task(id);
        taskRepository.delete(task);
        return Mapper.mapToTaskDto(task);
    }

    @Override
    public TaskDto updateTaskStatus(Long id, Status taskStatus) {
        Task task = task(id);
        task.setStatut(taskStatus);
        Task savedTask = taskRepository.save(task);
        return Mapper.mapToTaskDto(savedTask);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map((task) -> Mapper.mapToTaskDto(task))
                .toList();
    }

    private Task task(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }
}
