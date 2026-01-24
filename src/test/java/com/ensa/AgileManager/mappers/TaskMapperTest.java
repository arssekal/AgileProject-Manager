package com.ensa.AgileManager.mappers;

import com.ensa.AgileManager.dtos.TaskDto;
import com.ensa.AgileManager.entities.Task;
import com.ensa.AgileManager.enums.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests unitaires pour TaskMapper (MapStruct)
 * Utilise JUnit 5 et AssertJ
 */
@DisplayName("Tests unitaires pour TaskMapper")
class TaskMapperTest {

    @Test
    @DisplayName("Devrait mapper une entité Task vers TaskDto")
    void shouldMapTaskToDto() {
        // Given
        Task task = Task.builder()
                .id(1L)
                .titre("Tâche de test")
                .description("Description de la tâche")
                .statut(Status.TO_DO)
                .build();

        // When
        TaskDto taskDto = TaskMapper.INSTANCE.toDto(task);

        // Then
        assertThat(taskDto).isNotNull();
        assertThat(taskDto.getId()).isEqualTo(task.getId());
        assertThat(taskDto.getTitre()).isEqualTo(task.getTitre());
        assertThat(taskDto.getDescription()).isEqualTo(task.getDescription());
        assertThat(taskDto.getStatut()).isEqualTo(task.getStatut());
    }

    @Test
    @DisplayName("Devrait mapper un TaskDto vers une entité Task")
    void shouldMapDtoToTask() {
        // Given
        TaskDto taskDto = TaskDto.builder()
                .id(1L)
                .titre("Tâche de test")
                .description("Description de la tâche")
                .statut(Status.IN_PROGRESS)
                .build();

        // When
        Task task = TaskMapper.INSTANCE.toEntity(taskDto);

        // Then
        assertThat(task).isNotNull();
        assertThat(task.getId()).isEqualTo(taskDto.getId());
        assertThat(task.getTitre()).isEqualTo(taskDto.getTitre());
        assertThat(task.getDescription()).isEqualTo(taskDto.getDescription());
        assertThat(task.getStatut()).isEqualTo(taskDto.getStatut());
        // Les relations doivent être ignorées
        assertThat(task.getUserStory()).isNull();
        assertThat(task.getDeveloper()).isNull();
    }

    @Test
    @DisplayName("Devrait mapper null vers null")
    void shouldMapNullToNull() {
        // When
        TaskDto taskDto = TaskMapper.INSTANCE.toDto(null);
        Task task = TaskMapper.INSTANCE.toEntity(null);

        // Then
        assertThat(taskDto).isNull();
        assertThat(task).isNull();
    }
}
