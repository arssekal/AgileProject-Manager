package com.ensa.AgileManager.services.implementations;

import com.ensa.AgileManager.dtos.SprintBacklogDto;
import com.ensa.AgileManager.dtos.UserStoryDto;
import com.ensa.AgileManager.entities.*;
import com.ensa.AgileManager.exceptions.ProjectNotFoundException;
import com.ensa.AgileManager.exceptions.SprintBacklogNotFoundException;
import com.ensa.AgileManager.exceptions.UserStoryNotFoundException;
import com.ensa.AgileManager.mappers.SprintBacklogMapper;
import com.ensa.AgileManager.mappers.UserStoryMapper;
import com.ensa.AgileManager.repositories.*;
import com.ensa.AgileManager.services.interfaces.SprintBacklogService;
import com.ensa.AgileManager.services.interfaces.UserService;
import com.ensa.AgileManager.services.interfaces.UserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SprintBacklogServiceImpl implements SprintBacklogService {
    private final SprintBacklogRepository sprintBacklogRepository;
    private final SprintRepository sprintRepository;
    private final UserService userService;
    private final UserStoryRepository userStoryRepository;
    private final ProjectRepository projectRepository;

    @Override
    public SprintBacklogDto createSprint(Long projectId, SprintBacklogDto sprintBacklogDto) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        SprintBacklog sprintBacklog = SprintBacklogMapper.INSTANCE.toEntity(sprintBacklogDto);
        ScrumMaster scrumMaster = (ScrumMaster) userService.getUser(sprintBacklogDto.getScrumMasterId());

        Sprint sprint = Sprint.builder()
                .dateDebut(sprintBacklogDto.getDateDebut())
                .dateFin(sprintBacklogDto.getDateFin())
                .scrumMaster(scrumMaster)
                .sprintBacklog(sprintBacklog)
                .project(project)
                .build();
        SprintBacklog savedSprintBacklog = sprintBacklogRepository.save(sprintBacklog);
        sprintRepository.save(sprint);
        sprintBacklogDto.setId(savedSprintBacklog.getId());
        return sprintBacklogDto;
    }

    @Override
    public SprintBacklogDto getSprintDetails(Long id) {
        SprintBacklog sprintBacklog = sprintBacklog(id);
        return SprintBacklogMapper.INSTANCE.toDto(sprintBacklog);
    }

    @Override
    public UserStoryDto addUserStoryToSprint(Long sprintBacklogId, Long storyId) {
        SprintBacklog sprintBacklog = sprintBacklog(sprintBacklogId);
        UserStory userStory = userStoryRepository.findById(storyId).orElseThrow(() -> new UserStoryNotFoundException(storyId));
        userStory.setSprintBacklog(sprintBacklog);
        UserStory savedUserStory = userStoryRepository.save(userStory);
        return UserStoryMapper.INSTANCE.toDto(savedUserStory);
    }

    @Override
    public List<UserStoryDto> getSprintUserStories(Long id) {
        SprintBacklog sprintBacklog = sprintBacklog(id);
        return sprintBacklog.getUserStories().stream()
                .map((userStory -> UserStoryMapper.INSTANCE.toDto(userStory)))
                .toList();
    }

    @Override
    public UserStoryDto removeUserStory(Long sprintBacklogId, Long storyId) {
        SprintBacklog sprintBacklog = sprintBacklog(sprintBacklogId);
        UserStory userStory = userStoryRepository.findById(storyId).orElseThrow(() -> new UserStoryNotFoundException(storyId));
        userStory.setSprintBacklog(null);
        userStoryRepository.save(userStory);
        return UserStoryMapper.INSTANCE.toDto(userStory);
    }
    private SprintBacklog sprintBacklog(Long id) {
        return sprintBacklogRepository.findById(id).orElseThrow(() -> new SprintBacklogNotFoundException(id));
    }
}
