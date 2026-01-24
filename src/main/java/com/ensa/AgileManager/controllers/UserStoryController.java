package com.ensa.AgileManager.controllers;

import com.ensa.AgileManager.dtos.TaskDto;
import com.ensa.AgileManager.dtos.UserStoryDto;
import com.ensa.AgileManager.enums.Status;
import com.ensa.AgileManager.services.interfaces.UserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-stories")
@RequiredArgsConstructor
public class UserStoryController {
    private final UserStoryService userStoryService;

    // Récupérer la User Story.
    @GetMapping("/{id}")
    public ResponseEntity<UserStoryDto> getUserStory(@PathVariable("id") Long userStoryId) {
        UserStoryDto userStoryDto = userStoryService.getUserStory(userStoryId);
        return ResponseEntity.ok(userStoryDto);
    }
    // Modifier les attributs (titre, description, statut).
    @PutMapping("/{id}")
    public ResponseEntity<UserStoryDto> updateUserStory(@PathVariable("id") Long userStoryId, @RequestBody UserStoryDto userStoryDto) {
        UserStoryDto userStory = userStoryService.updateUserStory(userStoryId, userStoryDto);
        return ResponseEntity.ok(userStory);
    }
    // Créer une Tâche pour cette User Story.
    @PostMapping("/{id}/tasks")
    public ResponseEntity<TaskDto> createTask(@PathVariable("id") Long userStoryId, @RequestBody TaskDto taskDto) {
        TaskDto task = userStoryService.createTask(userStoryId, taskDto);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }
    // Lister toutes les Tâches de cette User Story.
    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskDto>> getUserStoryTasks(@PathVariable("id") Long userStoryId) {
        List<TaskDto> tasks = userStoryService.getUserStoryTasks(userStoryId);
        return ResponseEntity.ok(tasks);
    }
    // Supprimer la User Story.
    @DeleteMapping("/{id}")
    public ResponseEntity<UserStoryDto> deleteUseStoryWithTasks(@PathVariable("id") Long userStoryId) {
        UserStoryDto userStoryDto =  userStoryService.deleteUseStoryWithTasks(userStoryId);
        return ResponseEntity.ok(userStoryDto);
    }
    @PutMapping("/{id}/change-status")
    public ResponseEntity<UserStoryDto> changeUserStoryStatus(@PathVariable Long id, @RequestParam Status status) {
        UserStoryDto userStoryDto = userStoryService.changeUserStoryStatus(id, status);
        return ResponseEntity.ok(userStoryDto);
    }
}
