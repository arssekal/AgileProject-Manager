package com.ensa.AgileManager.services.implementations;

import com.ensa.AgileManager.dtos.EpicDto;
import com.ensa.AgileManager.dtos.UserStoryDto;
import com.ensa.AgileManager.entities.Epic;
import com.ensa.AgileManager.entities.UserStory;
import com.ensa.AgileManager.exceptions.EpicNotFoundException;
import com.ensa.AgileManager.mappers.EpicMapper;
import com.ensa.AgileManager.mappers.UserStoryMapper;
import com.ensa.AgileManager.repositories.EpicRepository;
import com.ensa.AgileManager.repositories.UserStoryRepository;
import com.ensa.AgileManager.services.interfaces.EpicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpicServiceImpl implements EpicService {
    private final EpicRepository epicRepository;
    private final UserStoryRepository userStoryRepository;

    @Override
    public EpicDto getEpicDetails(Long id) {
        Epic epic = epic(id);
        return EpicMapper.INSTANCE.toDto(epic);
    }

    @Override
    public EpicDto updateEpic(Long id, EpicDto epicDto) {
        Epic epic = epic(id);
        epic.setTitre(epicDto.getTitre());
        epic.setDescription(epicDto.getDescription());
        Epic updatedEpic = epicRepository.save(epic);
        return EpicMapper.INSTANCE.toDto(updatedEpic);
    }

    @Override
    public UserStoryDto addUserStoryToEpic(Long epicId, UserStoryDto userStoryDto) {
        Epic epic = epic(epicId);
        UserStory userStory = UserStoryMapper.INSTANCE.toEntity(userStoryDto);
        userStory.setEpic(epic);
        UserStory savedUserStory = userStoryRepository.save(userStory);
        return UserStoryMapper.INSTANCE.toDto(savedUserStory);
    }

    @Override
    public List<UserStoryDto> getAllEpicUserStories(Long epicId) {
        Epic epic = epic(epicId);
        return epic.getUserStories().stream()
                .map((userStory) -> {
                    return UserStoryMapper.INSTANCE.toDto(userStory);
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
        return EpicMapper.INSTANCE.toDto(epic);
    }

    @Override
    public EpicDto deleteEpicAndUserStories(Long epicId) {
        Epic epic = epic(epicId);
        epic.getUserStories().forEach((userStory -> {
            userStory.setEpic(null);
            userStoryRepository.delete(userStory);
        }));
        epicRepository.delete(epic);
        return EpicMapper.INSTANCE.toDto(epic);
    }

    private Epic epic(Long id) {
        return epicRepository.findById(id).orElseThrow(() -> new EpicNotFoundException(id));
    }
}
