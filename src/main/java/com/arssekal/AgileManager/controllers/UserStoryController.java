package com.arssekal.AgileManager.controllers;

import com.arssekal.AgileManager.dtos.TaskDto;
import com.arssekal.AgileManager.dtos.UserStoryDto;
import com.arssekal.AgileManager.services.interfaces.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-stories")
public class UserStoryController {
    @Autowired
    private UserStoryService userStoryService;

    // Récupérer la User Story.
    @GetMapping("{id}")
    public UserStoryDto getUserStory(@PathVariable("id") Long userStoryId) {
        return userStoryService.getUserStory(userStoryId);
    }
    // Modifier les attributs (titre, description, statut).
    @PutMapping("{id}")
    public UserStoryDto updateUserStory(@PathVariable("id") Long userStoryId, @RequestBody UserStoryDto userStoryDto) {
        return userStoryService.updateUserStory(userStoryId, userStoryDto);
    }
    // Créer une Tâche pour cette User Story.
    @PostMapping("{id}/tasks")
    public TaskDto createTask(@PathVariable("id") Long userStoryId, @RequestBody TaskDto taskDto) {
        return userStoryService.createTask(userStoryId, taskDto);
    }
    // Lister toutes les Tâches de cette User Story.
    @GetMapping("{id}/tasks")
    public List<TaskDto> getUserStoryTasks(@PathVariable("id") Long userStoryId) {
        return userStoryService.getUserStoryTasks(userStoryId);
    }
    // Supprimer la User Story.
    @DeleteMapping("{id}")
    public UserStoryDto deleteUseStoryWithTasks(@PathVariable("id") Long userStoryId) {
        return userStoryService.deleteUseStoryWithTasks(userStoryId);
    }
    @PutMapping("")
    public void changeUserStoryStatus() {
        // do something
    }
}
