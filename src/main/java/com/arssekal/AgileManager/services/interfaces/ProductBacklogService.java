package com.arssekal.AgileManager.services.interfaces;

import com.arssekal.AgileManager.dtos.EpicDto;
import com.arssekal.AgileManager.dtos.ProductBacklogDto;
import com.arssekal.AgileManager.dtos.UserStoryDto;

import java.util.List;

public interface ProductBacklogService {
    ProductBacklogDto getProductBacklog(Long id);

    EpicDto addEpic(Long backlogId, EpicDto epicDto);

    List<EpicDto> getProductBacklogEpics(Long backlogId);

    List<UserStoryDto> getProductBacklogUserStories(Long backlogId);
}
