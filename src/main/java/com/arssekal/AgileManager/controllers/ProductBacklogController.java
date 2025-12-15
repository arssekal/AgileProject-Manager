package com.arssekal.AgileManager.controllers;

import com.arssekal.AgileManager.dtos.EpicDto;
import com.arssekal.AgileManager.dtos.ProductBacklogDto;
import com.arssekal.AgileManager.dtos.UserStoryDto;
import com.arssekal.AgileManager.services.interfaces.ProductBacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backlogs/")
public class ProductBacklogController {
    @Autowired
    private ProductBacklogService productBacklogService;

    // Récupérer les détails du Product Backlog.
    @GetMapping("{id}")
    public ResponseEntity<?> getProductBacklog(@PathVariable Long id) {
        ProductBacklogDto productBacklog = productBacklogService.getProductBacklog(id);
        return ResponseEntity.ok(productBacklog);
    }
    // Créer un nouvel Epic dans ce Product Backlog.
    @PostMapping("{id}/epics")
    public ResponseEntity<?> addEpic(@PathVariable("id") Long backlogId, @RequestBody EpicDto epicDto) {
        EpicDto epic = productBacklogService.addEpic(backlogId, epicDto);
        return new ResponseEntity<>(epic, HttpStatus.CREATED);
    }
    // Lister tous les Epics du Product Backlog.
    @GetMapping("{id}/epics")
    public ResponseEntity<?> getEpics(@PathVariable("id") Long backlogId) {
        List<EpicDto> epics = productBacklogService.getProductBacklogEpics(backlogId);
        return ResponseEntity.ok(epics);
    }
    // Lister TOUTES les User Stories.
    @GetMapping("{id}/user-stories")
    public ResponseEntity<?> getUserStories(@PathVariable("id") Long backlogId) {
        List<UserStoryDto> userStories = productBacklogService.getProductBacklogUserStories(backlogId);
        return ResponseEntity.ok(userStories);
    }
}
