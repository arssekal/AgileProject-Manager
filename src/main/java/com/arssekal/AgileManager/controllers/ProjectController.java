package com.arssekal.AgileManager.controllers;

import com.arssekal.AgileManager.dtos.ProductBacklogDto;
import com.arssekal.AgileManager.dtos.ProjectDto;
import com.arssekal.AgileManager.entities.Project;
import com.arssekal.AgileManager.mappers.Mapper;
import com.arssekal.AgileManager.services.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// /api/projects
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/")
    public void createProject(@RequestBody ProjectDto projectDto) {
        projectService.createProject(projectDto);
    }

    @GetMapping("/{id}")
    public ProjectDto getProject(@PathVariable("id") Long projectID) {
        Project project = projectService.getProject(projectID);
        return Mapper.mapToProjectDto(project);
    }

    @PutMapping("/{id}")
    public ProjectDto updateProject(@PathVariable("id") Long projectID, @RequestBody ProjectDto newProjectDto) {
        return projectService.updateProjectInfos(projectID, newProjectDto);
    }

    @DeleteMapping("/{id}")
    public ProjectDto deleteProject(@PathVariable("id") Long projectID) {
        return projectService.deleteProject(projectID);
    }

    @GetMapping("/{id}/backlog")
    public ProductBacklogDto getProductBacklog(@PathVariable("id") Long projectID) {
        return projectService.getProductBacklog(projectID);
    }
}
