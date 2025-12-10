package com.arssekal.AgileManager.services.interfaces;

import com.arssekal.AgileManager.dtos.ProductBacklogDto;
import com.arssekal.AgileManager.dtos.ProjectDto;
import com.arssekal.AgileManager.entities.Project;

public interface ProjectService {
    void createProject(ProjectDto projectDto);

    Project getProject(Long projectID);

    ProjectDto updateProjectInfos(Long projectID, ProjectDto newProjectDto);

    ProjectDto deleteProject(Long projectID);

    ProductBacklogDto getProductBacklog(Long projectID);
}
