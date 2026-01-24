package com.ensa.AgileManager.mappers;

import com.ensa.AgileManager.dtos.TaskDto;
import com.ensa.AgileManager.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto toDto(Task task);

    @Mapping(target = "userStory", ignore = true)
    @Mapping(target = "developer", ignore = true)
    Task toEntity(TaskDto taskDto);
}
