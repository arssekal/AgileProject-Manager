package com.arssekal.AgileManager.mappers;

import com.arssekal.AgileManager.dtos.*;
import com.arssekal.AgileManager.entities.*;

public class Mapper {
    // epic mapping
    public static Epic mapToEpic(EpicDto epicDto) {
        return Epic.builder()
                .id(epicDto.getId())
                .titre(epicDto.getTitre())
                .description(epicDto.getDescription())
                .userStories(null)
                .build();
    }
    public static EpicDto mapToEpicDto(Epic epic) {
       return EpicDto.builder()
               .id(epic.getId())
               .titre(epic.getTitre())
               .description(epic.getDescription())
               .userStories(null)
               .build();
    }
    // product Backlog mapping
    public static ProductBacklog mapToProductBacklog(ProductBacklogDto productBacklogDto) {
        return ProductBacklog.builder()
                .id(productBacklogDto.getId())
                .nom(productBacklogDto.getNom())
                .epics(null)
                .build();
    }
    public static ProductBacklogDto mapToProductBacklogDto(ProductBacklog productBacklog) {
        return ProductBacklogDto.builder()
                .id(productBacklog.getId())
                .nom(productBacklog.getNom())
                .epics(null)
                .build();
    }
    // sprint Backlog mapping
    public static SprintBacklog mapToSprintBacklog(SprintBacklogDto sprintBacklogDto) {
        return SprintBacklog.builder()
                .id(sprintBacklogDto.getId())
                .nom(sprintBacklogDto.getNom())
                .UserStories(null)
                .build();
    }
    public static SprintBacklogDto mapToSprintBacklogDto(SprintBacklog sprintBacklog) {
        return SprintBacklogDto.builder()
                .id(sprintBacklog.getId())
                .nom(sprintBacklog.getNom())
                .UserStories(null)
                .build();
    }
    // task mapping
    public static Task mapToTask(TaskDto taskDto) {
        return Task.builder()
                .id(taskDto.getId())
                .titre(taskDto.getTitre())
                .description(taskDto.getDescription())
                .statut(taskDto.getStatut())
                .UserStory(null)
                .build();
    }
    public static TaskDto mapToTaskDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .titre(task.getTitre())
                .description(task.getDescription())
                .statut(task.getStatut())
                .UserStory(null)
                .build();
    }
    // user mapping
    public static User mapToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .nom(userDto.getNom())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .nom(user.getNom())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
    // userStory mapping
    public static UserStory mapToUserStory(UserStoryDto userStoryDto) {
        return UserStory.builder()
                .id(userStoryDto.getId())
                .titre(userStoryDto.getTitre())
                .description(userStoryDto.getDescription())
                .priority(userStoryDto.getPriority())
                .statut(userStoryDto.getStatut())
                .critereAcceptation(userStoryDto.getCritereAcceptation())
                .epic(null)
                .sprintBacklog(null)
                .build();
    }
    public static UserStoryDto mapToUserStoryDto(UserStory userStory) {
        return UserStoryDto.builder()
                .id(userStory.getId())
                .titre(userStory.getTitre())
                .description(userStory.getDescription())
                .priority(userStory.getPriority())
                .statut(userStory.getStatut())
                .critereAcceptation(userStory.getCritereAcceptation())
                .epic(null)
                .sprintBacklog(null)
                .build();
    }
    // sprint mapping
    public static Sprint mapToSprint(SprintDto sprintDto) {
        return Sprint.builder()
                .id(sprintDto.getId())
                .dateDebut(sprintDto.getDateDebut())
                .dateFin(sprintDto.getDateFin())
                .build();
    }
    public static SprintDto mapToSprintDto(Sprint sprint) {
        return SprintDto.builder()
                .id(sprint.getId())
                .dateDebut(sprint.getDateDebut())
                .dateFin(sprint.getDateFin())
                .build();
    }
}
