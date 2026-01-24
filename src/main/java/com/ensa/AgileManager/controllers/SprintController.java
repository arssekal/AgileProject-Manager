package com.ensa.AgileManager.controllers;

import com.ensa.AgileManager.dtos.SprintBacklogDto;
import com.ensa.AgileManager.dtos.UserStoryDto;
import com.ensa.AgileManager.services.interfaces.SprintBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/sprints")
@RequiredArgsConstructor
public class SprintController {
    private final SprintBacklogService sprintBacklogService;

    @PostMapping("/{projectId}")
    public ResponseEntity<SprintBacklogDto> createSprint(@PathVariable Long projectId, @RequestBody SprintBacklogDto sprintBacklogDto) {
        SprintBacklogDto sprintBacklog = sprintBacklogService.createSprint(projectId, sprintBacklogDto);
        return new ResponseEntity<>(sprintBacklog, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SprintBacklogDto> getSprintDetails(@PathVariable Long id) {
        SprintBacklogDto sprintBacklog = sprintBacklogService.getSprintDetails(id);
        return ResponseEntity.ok(sprintBacklog);
    }
    @PostMapping("/{sprintBacklogId}/user-stories/{storyId}")
    public ResponseEntity<UserStoryDto> addUserStoryToSprint(@PathVariable("sprintBacklogId") Long sprintBacklogId, @PathVariable("storyId") Long storyId) {
        UserStoryDto userStory = sprintBacklogService.addUserStoryToSprint(sprintBacklogId, storyId);
        return ResponseEntity.ok(userStory);
    }
    @GetMapping("/{id}/user-stories")
    public ResponseEntity<List<UserStoryDto>> getSprintUserStories(@PathVariable("id") Long id) {
        List<UserStoryDto> userStories = sprintBacklogService.getSprintUserStories(id);
        return ResponseEntity.ok(userStories);
    }
    @DeleteMapping("/{id}/user-stories/{storyId}")
    public ResponseEntity<UserStoryDto> removeUserStory(@PathVariable("id") Long sprintBacklogId, @PathVariable("storyId") Long storyId) {
        UserStoryDto userStory = sprintBacklogService.removeUserStory(sprintBacklogId, storyId);
        return ResponseEntity.ok(userStory);
    }
}
