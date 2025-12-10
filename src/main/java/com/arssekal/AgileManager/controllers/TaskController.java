package com.arssekal.AgileManager.controllers;

import com.arssekal.AgileManager.dtos.TaskDto;
import com.arssekal.AgileManager.services.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks/")
public class TaskController {
    @Autowired
    private TaskService taskService;

    // Récupérer la Tache.
    @GetMapping("{id}")
    public TaskDto getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }
    // Modifier la Tache
    @PutMapping("{id}")
    public TaskDto updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        return taskService.updateTask(id, taskDto);
    }
    // Supprimer la Tache.
    @PutMapping("{id}")
    public TaskDto deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }
}
