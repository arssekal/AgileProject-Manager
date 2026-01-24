package com.ensa.AgileManager.mappers;

import com.ensa.AgileManager.dtos.EpicDto;
import com.ensa.AgileManager.entities.Epic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EpicMapper {
    EpicMapper INSTANCE = Mappers.getMapper(EpicMapper.class);
    EpicDto toDto(Epic epic);
    @Mapping(target = "productBacklog", ignore = true)
    @Mapping(target = "userStories", ignore = true)
    Epic toEntity(EpicDto epicDto);
}
