package com.arssekal.AgileManager.controllers;

import com.arssekal.AgileManager.dtos.ProductBacklogDto;
import com.arssekal.AgileManager.dtos.ProjectDto;
import com.arssekal.AgileManager.dtos.SprintBacklogDto;
import com.arssekal.AgileManager.entities.Project;
import com.arssekal.AgileManager.mappers.Mapper;
import com.arssekal.AgileManager.services.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projects = projectService.getAllProject();
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
