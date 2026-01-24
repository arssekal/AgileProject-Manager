package com.ensa.AgileManager.mappers;

import com.ensa.AgileManager.dtos.UserStoryDto;
import com.ensa.AgileManager.entities.UserStory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserStoryMapper {
    UserStoryMapper INSTANCE = Mappers.getMapper(UserStoryMapper.class);

    UserStoryDto toDto(UserStory userStory);

    @Mapping(target = "epic", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "sprintBacklog", ignore = true)
    UserStory toEntity(UserStoryDto userStoryDto);
}
