package com.arssekal.AgileManager.services.implementations;

import com.arssekal.AgileManager.dtos.EpicDto;
import com.arssekal.AgileManager.dtos.UserStoryDto;
import com.arssekal.AgileManager.entities.Epic;
import com.arssekal.AgileManager.entities.UserStory;
import com.arssekal.AgileManager.mappers.Mapper;
import com.arssekal.AgileManager.repositories.EpicRepository;
import com.arssekal.AgileManager.repositories.UserStoryRepository;
import com.arssekal.AgileManager.services.interfaces.EpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpicServiceImpl implements EpicService {
    @Autowired
    private EpicRepository epicRepository;
    @Autowired
    private UserStoryRepository userStoryRepository;

    @Override
    public EpicDto getEpicDetails(Long id) {
        Epic epic = epic(id);
        return Mapper.mapToEpicDto(epic);
    }

    @Override
    public EpicDto updateEpic(Long id, EpicDto epicDto) {
        Epic epic = epic(id);
        epic.setTitre(epicDto.getTitre());
        epic.setDescription(epicDto.getDescription());
        Epic updatedEpic = epicRepository.save(epic);
        return Mapper.mapToEpicDto(updatedEpic);
    }

    @Override
    public UserStoryDto addUserStoryToEpic(Long epicId, UserStoryDto userStoryDto) {
        Epic epic = epic(epicId);
        UserStory userStory = Mapper.mapToUserStory(userStoryDto);
        userStory.setEpic(epic);
        UserStory savedUserStory = userStoryRepository.save(userStory);
        return Mapper.mapToUserStoryDto(savedUserStory);
    }

    @Override
    public List<UserStoryDto> getAllEpicUserStories(Long epicId) {
        Epic epic = epic(epicId);
        return epic.getUserStories().stream()
                .map((userStory) -> {
                    return Mapper.mapToUserStoryDto(userStory);
                }).toList();
    }

    @Override
    public EpicDto deleteEpic(Long epicId) {
        Epic epic = epic(epicId);
        Epic globalEpic = epicRepository.findByTitre("globale").getFirst();
        epic.getUserStories().forEach((userStory -> {
            userStory.setEpic(globalEpic);
            userStoryRepository.save(userStory);
        }));
        epicRepository.delete(epic);
        return Mapper.mapToEpicDto(epic);
    }

    @Override
    public EpicDto deleteEpicAndUserStories(Long epicId) {
        Epic epic = epic(epicId);
        epic.getUserStories().forEach((userStory -> {
            userStory.setEpic(null);
            userStoryRepository.delete(userStory);
        }));
        epicRepository.delete(epic);
        return Mapper.mapToEpicDto(epic);
    }

    private Epic epic(Long id) {
        return epicRepository.findById(id).orElseThrow(() -> new RuntimeException("epic with id: "+id+ " is not found"));
    }
}
