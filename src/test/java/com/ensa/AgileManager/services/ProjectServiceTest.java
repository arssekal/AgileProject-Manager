package com.ensa.AgileManager.services;

import com.ensa.AgileManager.dtos.ProductBacklogDto;
import com.ensa.AgileManager.dtos.ProjectDto;
import com.ensa.AgileManager.entities.Epic;
import com.ensa.AgileManager.entities.ProductBacklog;
import com.ensa.AgileManager.entities.ProductOwner;
import com.ensa.AgileManager.entities.Project;
import com.ensa.AgileManager.enums.OtherStatus;
import com.ensa.AgileManager.exceptions.ProjectNotFoundException;
import com.ensa.AgileManager.repositories.EpicRepository;
import com.ensa.AgileManager.repositories.ProjectRepository;
import com.ensa.AgileManager.services.implementations.ProjectServiceImpl;
import com.ensa.AgileManager.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests unitaires pour ProjectService
 * Utilise JUnit 5, Mockito et AssertJ
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitaires pour ProjectService")
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private EpicRepository epicRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project project;
    private ProjectDto projectDto;
    private ProductOwner productOwner;

    @BeforeEach
    void setUp() {
        // Création d'un ProductOwner de test
        productOwner = ProductOwner.builder()
                .id(1L)
                .nom("Owner")
                .prenom("Test")
                .email("owner@test.com")
                .build();

        // Création d'un ProductBacklog de test
        ProductBacklog productBacklog = ProductBacklog.builder()
                .id(1L)
                .nom("Backlog Test")
                .description("Description backlog")
                .build();

        // Création d'un projet de test
        project = Project.builder()
                .id(1L)
                .nom("Projet Test")
                .description("Description du projet")
                .status(OtherStatus.IN_PROGRESS)
                .createdAt(LocalDate.now())
                .productOwner(productOwner)
                .productBacklog(productBacklog)
                .build();

        // Création d'un DTO de test
        ProductBacklogDto productBacklogDto = ProductBacklogDto.builder()
                .id(1L)
                .nom("Backlog Test")
                .description("Description backlog")
                .build();

        projectDto = ProjectDto.builder()
                .id(1L)
                .nom("Projet Test")
                .description("Description du projet")
                .status(OtherStatus.IN_PROGRESS)
                .createdAt(LocalDate.now())
                .productOwnerId(1L)
                .productBacklogData(productBacklogDto)
                .build();
    }

    @Test
    @DisplayName("Devrait récupérer un projet par son ID")
    void shouldGetProjectById() {
        // Given
        Long projectId = 1L;
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        // When
        ProjectDto result = projectService.getProject(projectId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(projectId);
        assertThat(result.getNom()).isEqualTo("Projet Test");
        verify(projectRepository, times(1)).findById(projectId);
    }

    @Test
    @DisplayName("Devrait lever une exception si le projet n'existe pas")
    void shouldThrowExceptionWhenProjectNotFound() {
        // Given
        Long projectId = 999L;
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> projectService.getProject(projectId))
                .isInstanceOf(ProjectNotFoundException.class);
        verify(projectRepository, times(1)).findById(projectId);
    }

    @Test
    @DisplayName("Devrait mettre à jour les informations d'un projet")
    void shouldUpdateProjectInfos() {
        // Given
        Long projectId = 1L;
        ProjectDto updatedProjectDto = ProjectDto.builder()
                .nom("Projet Mis à Jour")
                .description("Nouvelle description")
                .build();

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        // When
        ProjectDto result = projectService.updateProjectInfos(projectId, updatedProjectDto);

        // Then
        assertThat(result).isNotNull();
        verify(projectRepository, times(1)).findById(projectId);
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    @DisplayName("Devrait supprimer un projet")
    void shouldDeleteProject() {
        // Given
        Long projectId = 1L;
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        doNothing().when(projectRepository).delete(project);

        // When
        ProjectDto result = projectService.deleteProject(projectId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(projectId);
        verify(projectRepository, times(1)).findById(projectId);
        verify(projectRepository, times(1)).delete(project);
    }

    @Test
    @DisplayName("Devrait récupérer le ProductBacklog d'un projet")
    void shouldGetProductBacklog() {
        // Given
        Long projectId = 1L;
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        // When
        ProductBacklogDto result = projectService.getProductBacklog(projectId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("Backlog Test");
        verify(projectRepository, times(1)).findById(projectId);
    }

    @Test
    @DisplayName("Devrait récupérer tous les projets avec pagination")
    void shouldGetAllProjects() {
        // Given
        Project project2 = Project.builder()
                .id(2L)
                .nom("Projet 2")
                .description("Description 2")
                .status(OtherStatus.IN_PROGRESS)
                .createdAt(LocalDate.now())
                .build();

        List<Project> projects = Arrays.asList(project, project2);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Project> projectPage = new PageImpl<>(projects, pageable, projects.size());
        when(projectRepository.findAll(pageable)).thenReturn(projectPage);

        // When
        Page<ProjectDto> result = projectService.getAllProject(pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getTotalElements()).isEqualTo(2);
        verify(projectRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Devrait récupérer les 3 derniers projets")
    void shouldGetLastThreeProjects() {
        // Given
        Project project2 = Project.builder()
                .id(2L)
                .nom("Projet 2")
                .description("Description 2")
                .status(OtherStatus.IN_PROGRESS)
                .createdAt(LocalDate.now())
                .build();

        Project project3 = Project.builder()
                .id(3L)
                .nom("Projet 3")
                .description("Description 3")
                .status(OtherStatus.IN_PROGRESS)
                .createdAt(LocalDate.now())
                .build();

        List<Project> projects = Arrays.asList(project, project2, project3);
        when(projectRepository.findTop3ByOrderByCreatedAtDesc()).thenReturn(projects);

        // When
        List<ProjectDto> result = projectService.getLastThreeProjects();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(3);
        verify(projectRepository, times(1)).findTop3ByOrderByCreatedAtDesc();
    }
}
