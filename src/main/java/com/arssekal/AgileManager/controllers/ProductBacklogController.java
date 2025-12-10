package com.arssekal.AgileManager.controllers;

import com.arssekal.AgileManager.dtos.EpicDto;
import com.arssekal.AgileManager.dtos.ProductBacklogDto;
import com.arssekal.AgileManager.dtos.UserStoryDto;
import com.arssekal.AgileManager.services.interfaces.ProductBacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backlogs/")
public class ProductBacklogController {
    @Autowired
    private ProductBacklogService productBacklogService;

    // Récupérer les détails du Product Backlog.
    @GetMapping("{id}")
    public ProductBacklogDto getProductBacklog(@PathVariable Long id) {
        return productBacklogService.getProductBacklog(id);
    }
    // Créer un nouvel Epic dans ce Product Backlog.
    @PostMapping("{id}/epics")
    public EpicDto addEpic(@PathVariable("id") Long backlogId, @RequestBody EpicDto epicDto) {
       return productBacklogService.addEpic(backlogId, epicDto);
    }
    // Lister tous les Epics du Product Backlog.
    @GetMapping("{id}/epics")
    public List<EpicDto> getEpics(@PathVariable("id") Long backlogId) {
       return productBacklogService.getProductBacklogEpics(backlogId);
    }
    // Lister TOUTES les User Stories.
    @GetMapping("{id}/user-stories")
    public List<UserStoryDto> getUserStories(@PathVariable("id") Long backlogId) {
       return productBacklogService.getProductBacklogUserStories(backlogId);
    }
}
