package com.ensa.AgileManager.controllers;

import com.ensa.AgileManager.dtos.EpicDto;
import com.ensa.AgileManager.dtos.ProjectDto;
import com.ensa.AgileManager.dtos.UserStoryDto;
import com.ensa.AgileManager.services.interfaces.EpicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/epics")
@RequiredArgsConstructor
public class EpicController {
    private final EpicService epicService;

    // Récupérer les détails d'un Epic.
    @GetMapping("/{id}")
    public ResponseEntity<?> getEpicDetails(@PathVariable Long id) {
        EpicDto epic = epicService.getEpicDetails(id);
        return ResponseEntity.ok(epic);
    }
    // Modifier l'Epic (titre, description, statut).
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEpicDetails(@PathVariable Long id, @RequestBody EpicDto epicDto) {
        EpicDto epic = epicService.updateEpic(id, epicDto);
        return ResponseEntity.ok(epic);
    }
    // Créer une nouvelle User Story et l'associer à un Epic.
    @PostMapping("/{id}/user-stories")
    public ResponseEntity<?> addUserStory(@PathVariable("id") Long epicId, @RequestBody UserStoryDto userStoryDto) {
        UserStoryDto userStory = epicService.addUserStoryToEpic(epicId, userStoryDto);
        return new ResponseEntity<>(userStory, HttpStatus.CREATED);
    }
    // Lister toutes les User Stories de cet Epic.
    @GetMapping("/{id}/user-stories")
    public ResponseEntity<?> getAllEpicUserStories(@PathVariable("id") Long epicId) {
        List<UserStoryDto> userStories = epicService.getAllEpicUserStories(epicId);
        return ResponseEntity.ok(userStories);
    }
    // Supprimer l'Epic et ses user stories.
    @DeleteMapping("/{id}/all")
    public ResponseEntity<String> deleteEpicAndUserStories(@PathVariable("id") Long epicId) {
        EpicDto epic = epicService.deleteEpicAndUserStories(epicId);
        return ResponseEntity.ok("epic with its user stories deleted successfully");
    }
    // Supprimer l'Epic et mettre ses user stories dans epic général.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEpicOnly(@PathVariable("id") Long epicId) {
        EpicDto epic = epicService.deleteEpicAndUserStories(epicId);
        return ResponseEntity.ok("epic deleted successfully");
    }
}
