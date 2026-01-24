package com.ensa.AgileManager.services.interfaces;

import com.ensa.AgileManager.dtos.EpicDto;
import com.ensa.AgileManager.dtos.UserStoryDto;

import java.util.List;

public interface EpicService {
    EpicDto getEpicDetails(Long id);

    EpicDto updateEpic(Long id, EpicDto epicDto);

    UserStoryDto addUserStoryToEpic(Long epicId, UserStoryDto userStoryDto);

    List<UserStoryDto> getAllEpicUserStories(Long epicId);

    EpicDto deleteEpic(Long epicId);

    EpicDto deleteEpicAndUserStories(Long epicId);
}
