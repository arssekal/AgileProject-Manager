package com.arssekal.AgileManager.services.implementations;

import com.arssekal.AgileManager.dtos.ProductBacklogDto;
import com.arssekal.AgileManager.dtos.ProjectDto;
import com.arssekal.AgileManager.dtos.SprintBacklogDto;
import com.arssekal.AgileManager.entities.Epic;
import com.arssekal.AgileManager.entities.ProductBacklog;
import com.arssekal.AgileManager.entities.ProductOwner;
import com.arssekal.AgileManager.entities.Project;
import com.arssekal.AgileManager.exceptions.ProjectNotFoundException;
import com.arssekal.AgileManager.mappers.Mapper;
import com.arssekal.AgileManager.repositories.EpicRepository;
import com.arssekal.AgileManager.repositories.ProjectRepository;
import com.arssekal.AgileManager.services.interfaces.ProjectService;
import com.arssekal.AgileManager.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EpicRepository epicRepository;

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        ProductOwner productOwner = (ProductOwner) userService.getUser(projectDto.getProductOwnerId());
        Project project = Mapper.mapToProject(projectDto);
        project.setProductOwner(productOwner);
        ProductBacklog productBacklog = Mapper.mapToProductBacklog(projectDto.getProductBacklogData());
        Epic epic = Epic.builder()
                .titre("globale")
                .description("epic qui regroupe les epics globales")
                .build();

        epic.setProductBacklog(productBacklog);
        project.setProductBacklog(productBacklog);
        productBacklog.setProject(project);

        Project savedProject =  projectRepository.save(project);
        epicRepository.save(epic);
        return Mapper.mapToProjectDto(savedProject);
    }

    @Override
    public ProjectDto getProject(Long projectID) {
        Project project = project(projectID);
        return Mapper.mapToProjectDto(project);
    }

    @Override
    public ProjectDto updateProjectInfos(Long projectID, ProjectDto newProjectDto) {
        Project project = project(projectID);
        project.setNom(newProjectDto.getNom());
        project.setDescription(newProjectDto.getDescription());
        projectRepository.save(project);
        return Mapper.mapToProjectDto(project);
    }
    @Override
    public ProjectDto deleteProject(Long projectID) {
        Project project = project(projectID);
        projectRepository.delete(project);
        return Mapper.mapToProjectDto(project);
    }

    @Override
    public ProductBacklogDto getProductBacklog(Long projectID) {
        Project project = project(projectID);
        return Mapper.mapToProductBacklogDto(project.getProductBacklog());
    }

    @Override
    public List<ProjectDto> getAllProject() {
        return projectRepository.findAll().stream()
                .map((project) -> {
                    return Mapper.mapToProjectDto(project);
                })
                .toList();
    }

    @Override
    public List<SprintBacklogDto> getSprints(Long projectID) {
        Project project = project(projectID);
        return project.getSprints().stream()
                .map((sprint -> {
                    return SprintBacklogDto.builder()
                            .id(sprint.getId())
                            .nom(sprint.getSprintBacklog().getNom())
                            .description(sprint.getSprintBacklog().getDescription())
                            .dateDebut(sprint.getDateDebut())
                            .dateFin(sprint.getDateFin())
                            .build();
                })).toList();
    }

    @Override
    public List<ProjectDto> getLastThreeProjects() {
        return projectRepository.findTop3ByOrderByCreatedAtDesc().stream()
                .map((project -> {
                    return ProjectDto.builder()
                            .nom(project.getNom())
                            .description(project.getDescription())
                            .createdAt(project.getCreatedAt())
                            .status(project.getStatus())
                            .build();
                })).toList();
    }

    private Project project(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
    }
}
