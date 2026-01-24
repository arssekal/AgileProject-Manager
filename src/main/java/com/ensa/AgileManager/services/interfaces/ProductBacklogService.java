package com.ensa.AgileManager.services.interfaces;

import com.ensa.AgileManager.dtos.EpicDto;
import com.ensa.AgileManager.dtos.ProductBacklogDto;
import com.ensa.AgileManager.dtos.UserStoryDto;

import java.util.List;

public interface ProductBacklogService {
    ProductBacklogDto getProductBacklog(Long id);

    EpicDto addEpic(Long backlogId, EpicDto epicDto);

    List<EpicDto> getProductBacklogEpics(Long backlogId);

    List<UserStoryDto> getProductBacklogUserStories(Long backlogId);

    Long userStoriesOverView(Long backlogId);
}
