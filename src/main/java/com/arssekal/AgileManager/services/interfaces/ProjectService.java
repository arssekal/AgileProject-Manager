package com.arssekal.AgileManager.services.interfaces;

import com.arssekal.AgileManager.dtos.ProductBacklogDto;
import com.arssekal.AgileManager.dtos.ProjectDto;
import com.arssekal.AgileManager.entities.Project;

public interface ProjectService {
    ProjectDto createProject(ProjectDto projectDto);

    ProjectDto getProject(Long projectID);

    ProjectDto updateProjectInfos(Long projectID, ProjectDto newProjectDto);

    ProjectDto deleteProject(Long projectID);

    ProductBacklogDto getProductBacklog(Long projectID);
}
