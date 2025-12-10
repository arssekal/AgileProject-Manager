package com.arssekal.AgileManager.controllers;

import com.arssekal.AgileManager.dtos.EpicDto;
import com.arssekal.AgileManager.dtos.UserStoryDto;
import com.arssekal.AgileManager.services.interfaces.EpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/epics/")
public class EpicController {
    @Autowired
    private EpicService epicService;

    // Récupérer les détails d'un Epic.
    @GetMapping("{id}")
    public EpicDto getEpicDetails(@PathVariable Long id) {
        return epicService.getEpicDetails(id);
    }
    // Modifier l'Epic (titre, description, statut).
    @PutMapping("{id}")
    public EpicDto updateEpicDetails(@PathVariable Long id, @RequestBody EpicDto epicDto) {
        return epicService.updateEpic(id, epicDto);
    }
    // Créer une nouvelle User Story et l'associer à un Epic.
    @PostMapping("{id}/user-stories")
    public UserStoryDto addUserStory(@PathVariable("id") Long epicId, @RequestBody UserStoryDto userStoryDto) {
        return epicService.addUserStoryToEpic(epicId, userStoryDto);
    }
    // Lister toutes les User Stories de cet Epic.
    @GetMapping("{id}/user-stories")
    public List<UserStoryDto> getAllEpicUserStories(@PathVariable("id") Long epicId) {
        return epicService.getAllEpicUserStories(epicId);
    }
    // Supprimer l'Epic et ses user stories.
    @DeleteMapping("{id}/all")
    public EpicDto deleteEpicAndUserStories(@PathVariable("id") Long epicId) {
        return epicService.deleteEpicAndUserStories(epicId);
    }
    // Supprimer l'Epic et mettre ses user stories dans epic général.
    @DeleteMapping("{id}")
    public EpicDto deleteEpicOnly(@PathVariable("id") Long epicId) {
        return epicService.deleteEpic(epicId);
    }
}
