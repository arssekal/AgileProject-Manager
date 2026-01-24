package com.ensa.AgileManager.services;

import com.ensa.AgileManager.dtos.TaskDto;
import com.ensa.AgileManager.entities.Task;
import com.ensa.AgileManager.enums.Status;
import com.ensa.AgileManager.exceptions.TaskNotFoundException;
import com.ensa.AgileManager.mappers.TaskMapper;
import com.ensa.AgileManager.repositories.TaskRepository;
import com.ensa.AgileManager.services.implementations.TaskServiceImpl;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests unitaires pour TaskService
 * Utilise JUnit 5, Mockito et AssertJ
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitaires pour TaskService")
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;
    private TaskDto taskDto;

    @BeforeEach
    void setUp() {
        // Création d'une tâche de test
        task = Task.builder()
                .id(1L)
                .titre("Tâche de test")
                .description("Description de la tâche")
                .statut(Status.TO_DO)
                .build();

        // Création d'un DTO de test
        taskDto = TaskDto.builder()
                .id(1L)
                .titre("Tâche de test")
                .description("Description de la tâche")
                .statut(Status.TO_DO)
                .build();
    }

    @Test
    @DisplayName("Devrait récupérer une tâche par son ID")
    void shouldGetTaskById() {
        // Given
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // When
        TaskDto result = taskService.getTask(taskId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(taskId);
        assertThat(result.getTitre()).isEqualTo("Tâche de test");
        assertThat(result.getStatut()).isEqualTo(Status.TO_DO);
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    @DisplayName("Devrait lever une exception si la tâche n'existe pas")
    void shouldThrowExceptionWhenTaskNotFound() {
        // Given
        Long taskId = 999L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> taskService.getTask(taskId))
                .isInstanceOf(TaskNotFoundException.class);
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    @DisplayName("Devrait mettre à jour une tâche")
    void shouldUpdateTask() {
        // Given
        Long taskId = 1L;
        TaskDto updatedTaskDto = TaskDto.builder()
                .id(taskId)
                .titre("Tâche mise à jour")
                .description("Nouvelle description")
                .statut(Status.IN_PROGRESS)
                .build();

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // When
        TaskDto result = taskService.updateTask(taskId, updatedTaskDto);

        // Then
        assertThat(result).isNotNull();
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    @DisplayName("Devrait supprimer une tâche")
    void shouldDeleteTask() {
        // Given
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        doNothing().when(taskRepository).delete(task);

        // When
        TaskDto result = taskService.deleteTask(taskId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(taskId);
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).delete(task);
    }

    @Test
    @DisplayName("Devrait mettre à jour le statut d'une tâche")
    void shouldUpdateTaskStatus() {
        // Given
        Long taskId = 1L;
        Status newStatus = Status.DONE;
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // When
        TaskDto result = taskService.updateTaskStatus(taskId, newStatus);

        // Then
        assertThat(result).isNotNull();
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    @DisplayName("Devrait récupérer toutes les tâches avec pagination")
    void shouldGetAllTasksWithPagination() {
        // Given
        Task task2 = Task.builder()
                .id(2L)
                .titre("Tâche 2")
                .description("Description 2")
                .statut(Status.IN_PROGRESS)
                .build();

        List<Task> tasks = Arrays.asList(task, task2);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Task> taskPage = new PageImpl<>(tasks, pageable, tasks.size());

        when(taskRepository.findAll(pageable)).thenReturn(taskPage);

        // When
        Page<TaskDto> result = taskService.getAllTasks(pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.getNumber()).isEqualTo(0);
        assertThat(result.getSize()).isEqualTo(5);
        verify(taskRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Devrait retourner une page vide si aucune tâche n'existe")
    void shouldReturnEmptyPageWhenNoTasks() {
        // Given
        Pageable pageable = PageRequest.of(0, 5);
        Page<Task> emptyPage = new PageImpl<>(List.of(), pageable, 0);
        when(taskRepository.findAll(pageable)).thenReturn(emptyPage);

        // When
        Page<TaskDto> result = taskService.getAllTasks(pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).isEmpty();
        assertThat(result.getTotalElements()).isEqualTo(0);
        verify(taskRepository, times(1)).findAll(pageable);
    }
}
