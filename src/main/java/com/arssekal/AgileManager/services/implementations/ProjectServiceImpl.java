package com.arssekal.AgileManager.services.implementations;

import com.arssekal.AgileManager.dtos.ProductBacklogDto;
import com.arssekal.AgileManager.dtos.ProjectDto;
import com.arssekal.AgileManager.entities.Epic;
import com.arssekal.AgileManager.entities.ProductBacklog;
import com.arssekal.AgileManager.entities.ProductOwner;
import com.arssekal.AgileManager.entities.Project;
import com.arssekal.AgileManager.mappers.Mapper;
import com.arssekal.AgileManager.repositories.EpicRepository;
import com.arssekal.AgileManager.repositories.ProjectRepository;
import com.arssekal.AgileManager.services.interfaces.ProjectService;
import com.arssekal.AgileManager.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EpicRepository epicRepository;

    @Override
    public void createProject(ProjectDto projectDto) {
        ProductOwner productOwner = userService.getUser(projectDto.getProductOwnerId());
        Project project = Mapper.mapToProject(projectDto);
        project.setProductOwner(productOwner);
        productOwner.setProject(project);
        ProductBacklog productBacklog = Mapper.mapToProductBacklog(projectDto.getProductBacklogData());
        Epic epic = Epic.builder()
                .titre("globale")
                .description("epic qui regroupe les epics globales")
                .build();
        epic.setProductBacklog(productBacklog);

        project.setProductBacklog(productBacklog);
        productBacklog.setProject(project);

        projectRepository.save(project);
        epicRepository.save(epic);
    }

    @Override
    public Project getProject(Long projectID) {
        return projectRepository.findById(projectID).orElseThrow(() -> new RuntimeException("project with this id isn't found"));
    }

    @Override
    public ProjectDto updateProjectInfos(Long projectID, ProjectDto newProjectDto) {
        Project project = projectRepository.findById(projectID).orElseThrow(() -> new RuntimeException("project with this id isn't found"));
        project.setNom(newProjectDto.getNom());
        project.setDescription(newProjectDto.getDescription());
        projectRepository.save(project);
        return Mapper.mapToProjectDto(project);
    }
    @Override
    public ProjectDto deleteProject(Long projectID) {
        Project project = projectRepository.findById(projectID).orElseThrow(() -> new RuntimeException("project with this id isn't found"));
        project.getProductOwner().setProject(null);
        projectRepository.delete(project);
        return Mapper.mapToProjectDto(project);
    }

    @Override
    public ProductBacklogDto getProductBacklog(Long projectID) {
        Project project = projectRepository.findById(projectID).orElseThrow(() -> new RuntimeException("project with this id isn't found"));
        return Mapper.mapToProductBacklogDto(project.getProductBacklog());
    }
}
