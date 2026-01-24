package com.ensa.AgileManager.controllers;

import com.ensa.AgileManager.dtos.EpicDto;
import com.ensa.AgileManager.dtos.ProductBacklogDto;
import com.ensa.AgileManager.dtos.UserStoryDto;
import com.ensa.AgileManager.services.interfaces.ProductBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/backlogs")
@RequiredArgsConstructor
public class ProductBacklogController {
    private final ProductBacklogService productBacklogService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductBacklog(@PathVariable Long id) {
        ProductBacklogDto productBacklog = productBacklogService.getProductBacklog(id);
        return ResponseEntity.ok(productBacklog);
    }

    @PostMapping("/{id}/epics")
    public ResponseEntity<?> addEpic(@PathVariable("id") Long backlogId, @RequestBody EpicDto epicDto) {
        EpicDto epic = productBacklogService.addEpic(backlogId, epicDto);
        return new ResponseEntity<>(epic, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/epics")
    public ResponseEntity<?> getEpics(@PathVariable("id") Long backlogId) {
        List<EpicDto> epics = productBacklogService.getProductBacklogEpics(backlogId);
        return ResponseEntity.ok(epics);
    }

    @GetMapping("/{id}/user-stories")
    public ResponseEntity<?> getUserStories(@PathVariable("id") Long backlogId) {
        List<UserStoryDto> userStories = productBacklogService.getProductBacklogUserStories(backlogId);
        return ResponseEntity.ok(userStories);
    }

    @GetMapping("/{id}/user-stories/done")
    public ResponseEntity<?> countDoneUserStories(@PathVariable("id") Long backlogId) {
        Long countDonUserStories = productBacklogService.userStoriesOverView(backlogId);
        return ResponseEntity.ok(countDonUserStories);
    }
}
