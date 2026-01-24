package com.ensa.AgileManager.services.implementations;

import com.ensa.AgileManager.dtos.ProductBacklogDto;
import com.ensa.AgileManager.dtos.ProjectDto;
import com.ensa.AgileManager.dtos.SprintBacklogDto;
import com.ensa.AgileManager.entities.Epic;
import com.ensa.AgileManager.entities.ProductBacklog;
import com.ensa.AgileManager.entities.ProductOwner;
import com.ensa.AgileManager.entities.Project;
import com.ensa.AgileManager.exceptions.ProjectNotFoundException;
import com.ensa.AgileManager.mappers.ProductBacklogMapper;
import com.ensa.AgileManager.mappers.ProjectMapper;
import com.ensa.AgileManager.mappers.SprintBacklogMapper;
import com.ensa.AgileManager.repositories.EpicRepository;
import com.ensa.AgileManager.repositories.ProjectRepository;
import com.ensa.AgileManager.services.interfaces.ProjectService;
import com.ensa.AgileManager.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final UserService userService;
    private final ProjectRepository projectRepository;
    private final EpicRepository epicRepository;

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        ProductOwner productOwner = (ProductOwner) userService.getUser(projectDto.getProductOwnerId());
        Project project = ProjectMapper.INSTANCE.toEntity(projectDto);
        project.setProductOwner(productOwner);
        ProductBacklog productBacklog = ProductBacklogMapper.INSTANCE.toEntity(projectDto.getProductBacklogData());
        Epic epic = Epic.builder()
                .titre("globale")
                .description("epic qui regroupe les epics globales")
                .build();

        epic.setProductBacklog(productBacklog);
        project.setProductBacklog(productBacklog);
        productBacklog.setProject(project);

        Project savedProject =  projectRepository.save(project);
        epicRepository.save(epic);
        return ProjectMapper.INSTANCE.toDto(savedProject);
    }

    @Override
    public ProjectDto getProject(Long projectID) {
        Project project = project(projectID);
        return ProjectMapper.INSTANCE.toDto(project);
    }

    @Override
    public ProjectDto updateProjectInfos(Long projectID, ProjectDto newProjectDto) {
        Project project = project(projectID);
        project.setNom(newProjectDto.getNom());
        project.setDescription(newProjectDto.getDescription());
        projectRepository.save(project);
        return ProjectMapper.INSTANCE.toDto(project);
    }
    @Override
    public ProjectDto deleteProject(Long projectID) {
        Project project = project(projectID);
        projectRepository.delete(project);
        return ProjectMapper.INSTANCE.toDto(project);
    }

    @Override
    public ProductBacklogDto getProductBacklog(Long projectID) {
        Project project = project(projectID);
        return ProductBacklogMapper.INSTANCE.toDto(project.getProductBacklog());
    }

    @Override
    public Page<ProjectDto> getAllProject(Pageable pageable) {
        return projectRepository.findAll(pageable)
                .map(ProjectMapper.INSTANCE::toDto);
    }

    @Override
    public List<SprintBacklogDto> getSprints(Long projectID) {
        Project project = project(projectID);
        return project.getSprints().stream()
                .map((sprint -> SprintBacklogMapper.INSTANCE.toDtoFromSprint(sprint)))
                .toList();
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
