package com.ensa.AgileManager.mappers;

import com.ensa.AgileManager.dtos.SprintDto;
import com.ensa.AgileManager.entities.Sprint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SprintMapper {
    SprintMapper INSTANCE = Mappers.getMapper(SprintMapper.class);

    SprintDto toDto(Sprint sprint);

    @Mapping(target = "sprintBacklog", ignore = true)
    @Mapping(target = "scrumMaster", ignore = true)
    @Mapping(target = "project", ignore = true)
    Sprint toEntity(SprintDto sprintDto);
}
