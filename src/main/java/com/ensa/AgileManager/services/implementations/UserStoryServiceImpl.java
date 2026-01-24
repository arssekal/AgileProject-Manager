package com.ensa.AgileManager.services.implementations;

import com.ensa.AgileManager.dtos.TaskDto;
import com.ensa.AgileManager.dtos.UserStoryDto;
import com.ensa.AgileManager.entities.Task;
import com.ensa.AgileManager.entities.User;
import com.ensa.AgileManager.entities.UserStory;
import com.ensa.AgileManager.enums.Status;
import com.ensa.AgileManager.exceptions.UserStoryNotFoundException;
import com.ensa.AgileManager.mappers.TaskMapper;
import com.ensa.AgileManager.mappers.UserStoryMapper;
import com.ensa.AgileManager.repositories.TaskRepository;
import com.ensa.AgileManager.repositories.UserStoryRepository;
import com.ensa.AgileManager.services.interfaces.UserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStoryServiceImpl implements UserStoryService {
    private final UserStoryRepository userStoryRepository;
    private final TaskRepository taskRepository;

    @Override
    public UserStoryDto getUserStory(Long userStoryId) {
        UserStory userStory = userStory(userStoryId);
        return UserStoryMapper.INSTANCE.toDto(userStory);
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
        return UserStoryMapper.INSTANCE.toDto(savedUserStory);
    }

    @Override
    public TaskDto createTask(Long userStoryId, TaskDto taskDto) {
        UserStory userStory = userStory(userStoryId);
        Task task = TaskMapper.INSTANCE.toEntity(taskDto);
        task.setUserStory(userStory);
        Task savedTask = taskRepository.save(task);
        return TaskMapper.INSTANCE.toDto(savedTask);
    }

    @Override
    public List<TaskDto> getUserStoryTasks(Long userStoryId) {
        UserStory userStory = userStory(userStoryId);
        return userStory.getTasks().stream()
                .map((task -> TaskMapper.INSTANCE.toDto(task)))
                .toList();
    }

    @Override
    public UserStoryDto deleteUseStoryWithTasks(Long userStoryId) {
        UserStory userStory = userStory(userStoryId);
        userStory.getTasks().forEach((task -> {
            taskRepository.delete(task);
        }));
        userStoryRepository.delete(userStory);
        return UserStoryMapper.INSTANCE.toDto(userStory);
    }

    @Override
    public UserStoryDto changeUserStoryStatus(Long id, Status status) {
        UserStory userStory = userStory(id);
        userStory.setStatut(status);
        UserStory savedUserStory = userStoryRepository.save(userStory);
        return  UserStoryMapper.INSTANCE.toDto(savedUserStory);
    }

    private UserStory userStory(Long id) {
        return userStoryRepository.findById(id).orElseThrow(() -> new UserStoryNotFoundException(id));
    }
}
