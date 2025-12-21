package com.arssekal.AgileManager.controllers;

import com.arssekal.AgileManager.dtos.TaskDto;
import com.arssekal.AgileManager.enums.Status;
import com.arssekal.AgileManager.services.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    // Récupérer tous les Taches.
    @GetMapping("")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> taskDtos = taskService.getAllTasks();
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
