package com.arssekal.AgileManager.services.implementations;

import com.arssekal.AgileManager.dtos.SprintBacklogDto;
import com.arssekal.AgileManager.dtos.UserStoryDto;
import com.arssekal.AgileManager.entities.*;
import com.arssekal.AgileManager.exceptions.ProjectNotFoundException;
import com.arssekal.AgileManager.exceptions.SprintBacklogNotFoundException;
import com.arssekal.AgileManager.exceptions.UserStoryNotFoundException;
import com.arssekal.AgileManager.mappers.Mapper;
import com.arssekal.AgileManager.repositories.*;
import com.arssekal.AgileManager.services.interfaces.SprintBacklogService;
import com.arssekal.AgileManager.services.interfaces.UserService;
import com.arssekal.AgileManager.services.interfaces.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class SprintBacklogServiceImpl implements SprintBacklogService {
    @Autowired
    private SprintBacklogRepository sprintBacklogRepository;
    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserStoryRepository userStoryRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public SprintBacklogDto createSprint(Long projectId, SprintBacklogDto sprintBacklogDto) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        SprintBacklog sprintBacklog = Mapper.mapToSprintBacklog(sprintBacklogDto);
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
        SprintBacklogDto sprintBacklogDto = Mapper.mapToSprintBacklogDto(sprintBacklog);// id et nom
        sprintBacklogDto.setScrumMasterId(sprintBacklog.getSprint().getScrumMaster().getId());
        sprintBacklogDto.setDateDebut(sprintBacklog.getSprint().getDateDebut());
        sprintBacklogDto.setDateFin(sprintBacklog.getSprint().getDateFin());
        return sprintBacklogDto;
    }

    @Override
    public UserStoryDto addUserStoryToSprint(Long sprintBacklogId, Long storyId) {
        SprintBacklog sprintBacklog = sprintBacklog(sprintBacklogId);
        UserStory userStory = userStoryRepository.findById(storyId).orElseThrow(() -> new UserStoryNotFoundException(storyId));
        userStory.setSprintBacklog(sprintBacklog);
        UserStory savedUserStory = userStoryRepository.save(userStory);
        return Mapper.mapToUserStoryDto(savedUserStory);
    }

    @Override
    public List<UserStoryDto> getSprintUserStories(Long id) {
        SprintBacklog sprintBacklog = sprintBacklog(id);
        return sprintBacklog.getUserStories().stream()
                .map((userStory -> Mapper.mapToUserStoryDto(userStory)))
                .toList();
    }

    @Override
    public UserStoryDto removeUserStory(Long sprintBacklogId, Long storyId) {
        SprintBacklog sprintBacklog = sprintBacklog(sprintBacklogId);
        UserStory userStory = userStoryRepository.findById(storyId).orElseThrow(() -> new UserStoryNotFoundException(storyId));
        userStory.setSprintBacklog(null);
        userStoryRepository.save(userStory);
        return Mapper.mapToUserStoryDto(userStory);
    }
    private SprintBacklog sprintBacklog(Long id) {
        return sprintBacklogRepository.findById(id).orElseThrow(() -> new SprintBacklogNotFoundException(id));
    }
}
