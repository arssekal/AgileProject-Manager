package com.ensa.AgileManager.controllers;

import com.ensa.AgileManager.dtos.TaskDto;
import com.ensa.AgileManager.enums.Status;
import com.ensa.AgileManager.services.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    // Récupérer tous les Taches avec pagination (taille par défaut: 5)
    @GetMapping("")
    public ResponseEntity<Page<TaskDto>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskDto> taskDtos = taskService.getAllTasks(pageable);
        return ResponseEntity.ok(taskDtos);
    }
    // Récupérer la Tache.
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        TaskDto taskDto = taskService.getTask(id);
        return ResponseEntity.ok(taskDto);
    }
    // Modifier la Tache
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        TaskDto task = taskService.updateTask(id, taskDto);
        return ResponseEntity.ok(task);
    }
    // Supprimer la Tache.
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable Long id) {
        TaskDto taskDto = taskService.deleteTask(id);
        return ResponseEntity.ok(taskDto);
    }
    @PutMapping("/{id}/change-status")
    public ResponseEntity<TaskDto> updateTaskStatus(@PathVariable Long id, @RequestParam("status") Status taskStatus) {
        TaskDto taskDto = taskService.updateTaskStatus(id, taskStatus);
        return ResponseEntity.ok(taskDto);
    }
}
