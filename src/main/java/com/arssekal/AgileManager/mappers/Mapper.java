package com.arssekal.AgileManager.mappers;

import com.arssekal.AgileManager.dtos.*;
import com.arssekal.AgileManager.entities.*;

public class Mapper {
    // project mapping
    public static Project mapToProject(ProjectDto projectDto) {
        return Project.builder()
                .nom(projectDto.getNom())
                .description(projectDto.getDescription())
                .build();
    }
    public static ProjectDto mapToProjectDto(Project project) {
        return ProjectDto.builder()
                .nom(project.getNom())
                .description(project.getDescription())
                .productOwnerId(project.getProductOwner().getId())
                .build();
    }
    // epic mapping
    public static Epic mapToEpic(EpicDto epicDto) {
        return Epic.builder()
                .titre(epicDto.getTitre())
                .description(epicDto.getDescription())
//                .userStories(epicDto.getUserStories().stream().map(
//                        (userStoryDto -> Mapper.mapToUserStory(userStoryDto))
//                ).toList())
                .build();
    }
    public static EpicDto mapToEpicDto(Epic epic) {
       return EpicDto.builder()
               .titre(epic.getTitre())
               .description(epic.getDescription())
//               .userStories(epic.getUserStories().stream().map(
//                       (userStory -> Mapper.mapToUserStoryDto(userStory))
//               ).toList())
               .build();
    }
    // product Backlog mapping
    public static ProductBacklog mapToProductBacklog(ProductBacklogDto productBacklogDto) {
        return ProductBacklog.builder()
                .nom(productBacklogDto.getNom())
                .description(productBacklogDto.getDescription())
                .build();
    }
    public static ProductBacklogDto mapToProductBacklogDto(ProductBacklog productBacklog) {
        return ProductBacklogDto.builder()
                .nom(productBacklog.getNom())
                .description(productBacklog.getDescription())
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
                .userStories(null)
                .build();
    }
    // task mapping
    public static Task mapToTask(TaskDto taskDto) {
        return Task.builder()
                .titre(taskDto.getTitre())
                .description(taskDto.getDescription())
                .statut(taskDto.getStatut())
                .build();
    }
    public static TaskDto mapToTaskDto(Task task) {
        return TaskDto.builder()
                .titre(task.getTitre())
                .description(task.getDescription())
                .statut(task.getStatut())
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
                .titre(userStoryDto.getTitre())
                .description(userStoryDto.getDescription())
                .priority(userStoryDto.getPriority())
                .statut(userStoryDto.getStatut())
                .critereAcceptation(userStoryDto.getCritereAcceptation())
//                .epic(null)
//                .sprintBacklog(null)
                .build();
    }
    public static UserStoryDto mapToUserStoryDto(UserStory userStory) {
        return UserStoryDto.builder()
                .titre(userStory.getTitre())
                .description(userStory.getDescription())
                .priority(userStory.getPriority())
                .statut(userStory.getStatut())
                .critereAcceptation(userStory.getCritereAcceptation())
//                .epic(null)
//                .sprintBacklog(null)
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
