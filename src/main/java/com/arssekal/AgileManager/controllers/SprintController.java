package com.arssekal.AgileManager.controllers;

import com.arssekal.AgileManager.dtos.SprintBacklogDto;
import com.arssekal.AgileManager.dtos.UserStoryDto;
import com.arssekal.AgileManager.services.interfaces.SprintBacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
public class SprintController {
    @Autowired
    private SprintBacklogService sprintBacklogService;

    // Créer un nouveau Sprint.
    @PostMapping
    public ResponseEntity<SprintBacklogDto> createSprint(@RequestBody SprintBacklogDto sprintBacklogDto) {
        SprintBacklogDto sprintBacklog = sprintBacklogService.createSprint(sprintBacklogDto);
        return new ResponseEntity<>(sprintBacklog, HttpStatus.CREATED);
    }
    // Récupérer les détails du Sprint.
    @GetMapping("/{id}")
    public ResponseEntity<SprintBacklogDto> getSprintDetails(@PathVariable Long id) {
        SprintBacklogDto sprintBacklog = sprintBacklogService.getSprintDetails(id);
        return ResponseEntity.ok(sprintBacklog);
    }
    // Ajouter une User Story au Sprint.
    @PostMapping("/{sprintBacklogId}/user-stories/{storyId}")
    public ResponseEntity<UserStoryDto> addUserStoryToSprint(@PathVariable("sprintBacklogId") Long sprintBacklogId, @PathVariable("storyId") Long storyId) {
        UserStoryDto userStory = sprintBacklogService.addUserStoryToSprint(sprintBacklogId, storyId);
        return ResponseEntity.ok(userStory);
    }
    // Lister toutes les User Stories contenues dans ce Sprint.
    @GetMapping("/{id}/user-stories")
    public ResponseEntity<List<UserStoryDto>> getSprintUserStories(@PathVariable("id") Long id) {
        List<UserStoryDto> userStories = sprintBacklogService.getSprintUserStories(id);
        return ResponseEntity.ok(userStories);
    }
    // Retirer une User Story du Sprint.
    @DeleteMapping("/{id}/user-stories/{storyId}")
    public ResponseEntity<UserStoryDto> removeUserStory(@PathVariable("id") Long sprintBacklogId, @PathVariable("storyId") Long storyId) {
        UserStoryDto userStory = sprintBacklogService.removeUserStory(sprintBacklogId, storyId);
        return ResponseEntity.ok(userStory);
    }
}
