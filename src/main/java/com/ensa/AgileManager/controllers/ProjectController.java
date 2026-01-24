package com.ensa.AgileManager.controllers;

import com.ensa.AgileManager.dtos.ProductBacklogDto;
import com.ensa.AgileManager.dtos.ProjectDto;
import com.ensa.AgileManager.dtos.SprintBacklogDto;
import com.ensa.AgileManager.services.interfaces.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<Page<ProjectDto>> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProjectDto> projects = projectService.getAllProject(pageable);
        return ResponseEntity.ok(projects);
    }
    @GetMapping("last-three")
    public ResponseEntity<List<ProjectDto>> getLastThreeProjects() {
        List<ProjectDto> projects = projectService.getLastThreeProjects();
        return ResponseEntity.ok(projects);
    }
    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        ProjectDto project = projectService.createProject(projectDto);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable Long id) {
        // exception gérer par controller advise
        ProjectDto project = projectService.getProject(id);
        return ResponseEntity.ok(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable("id") Long projectID, @RequestBody ProjectDto newProjectDto) {
        ProjectDto project = projectService.updateProjectInfos(projectID, newProjectDto);
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") Long projectID) {
        ProjectDto project = projectService.deleteProject(projectID);
        return ResponseEntity.ok("project deleted successfully");
    }

    @GetMapping("/{id}/backlog")
    public ResponseEntity<?> getProductBacklog(@PathVariable("id") Long projectID) {
        ProductBacklogDto productBacklog = projectService.getProductBacklog(projectID);
        return ResponseEntity.ok(productBacklog);
    }

    @GetMapping("/{id}/sprints")
    public ResponseEntity<?> getSprints(@PathVariable("id") Long projectID) {
        List<SprintBacklogDto>  sprints = projectService.getSprints(projectID);
        return ResponseEntity.ok(sprints);
    }
}
