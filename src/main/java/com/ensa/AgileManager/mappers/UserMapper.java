package com.ensa.AgileManager.mappers;

import com.ensa.AgileManager.dtos.UserDto;
import com.ensa.AgileManager.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);

    @Mapping(target = "sprints", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "task", ignore = true)
    User toEntity(UserDto userDto);
}
