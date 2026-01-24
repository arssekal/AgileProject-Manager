package com.ensa.AgileManager.services.interfaces;

import com.ensa.AgileManager.dtos.ProductBacklogDto;
import com.ensa.AgileManager.dtos.ProjectDto;
import com.ensa.AgileManager.dtos.SprintBacklogDto;
import com.ensa.AgileManager.entities.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    ProjectDto createProject(ProjectDto projectDto);

    ProjectDto getProject(Long projectID);

    ProjectDto updateProjectInfos(Long projectID, ProjectDto newProjectDto);

    ProjectDto deleteProject(Long projectID);

    ProductBacklogDto getProductBacklog(Long projectID);

    Page<ProjectDto> getAllProject(Pageable pageable);

    List<SprintBacklogDto> getSprints(Long projectID);

    List<ProjectDto> getLastThreeProjects();
}
