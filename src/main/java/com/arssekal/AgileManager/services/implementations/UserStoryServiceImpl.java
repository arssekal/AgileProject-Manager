package com.arssekal.AgileManager.services.implementations;

import com.arssekal.AgileManager.dtos.TaskDto;
import com.arssekal.AgileManager.dtos.UserStoryDto;
import com.arssekal.AgileManager.entities.Task;
import com.arssekal.AgileManager.entities.UserStory;
import com.arssekal.AgileManager.mappers.Mapper;
import com.arssekal.AgileManager.repositories.TaskRepository;
import com.arssekal.AgileManager.repositories.UserStoryRepository;
import com.arssekal.AgileManager.services.interfaces.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStoryServiceImpl implements UserStoryService {
    @Autowired
    private UserStoryRepository userStoryRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public UserStoryDto getUserStory(Long userStoryId) {
        UserStory userStory = userStory(userStoryId);
        return Mapper.mapToUserStoryDto(userStory);
    }

    @Override
    public UserStoryDto updateUserStory(Long userStoryId, UserStoryDto userStoryDto) {
        UserStory userStory = userStory(userStoryId);
        userStory.setTitre(userStoryDto.getTitre());
        userStory.setDescription(userStoryDto.getDescription());
        userStory.setPriority(userStoryDto.getPriority());
        userStory.setStatut(userStoryDto.getStatut());
        userStory.setCritereAcceptation(userStoryDto.getCritereAcceptation());
        UserStory savedUserStory = userStoryRepository.save(userStory);
        return Mapper.mapToUserStoryDto(savedUserStory);
    }

    @Override
    public TaskDto createTask(Long userStoryId, TaskDto taskDto) {
        UserStory userStory = userStory(userStoryId);
        Task task = Mapper.mapToTask(taskDto);
        task.setUserStory(userStory);
        Task savedTask = taskRepository.save(task);
        return Mapper.mapToTaskDto(savedTask);
    }

    @Override
    public List<TaskDto> getUserStoryTasks(Long userStoryId) {
        UserStory userStory = userStory(userStoryId);
        return userStory.getTasks().stream()
                .map((task -> Mapper.mapToTaskDto(task)))
                .toList();
    }

    @Override
    public UserStoryDto deleteUseStoryWithTasks(Long userStoryId) {
        UserStory userStory = userStory(userStoryId);
        userStory.getTasks().forEach((task -> {
            taskRepository.delete(task);
        }));
        userStoryRepository.delete(userStory);
        return Mapper.mapToUserStoryDto(userStory);
    }

    private UserStory userStory(Long id) {
        return userStoryRepository.findById(id).orElseThrow(() -> new RuntimeException("user story with id "+ id+ " is not found"));
    }
}
