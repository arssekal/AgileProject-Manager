package com.ensa.AgileManager.mappers;

import com.ensa.AgileManager.dtos.ProductBacklogDto;
import com.ensa.AgileManager.dtos.ProjectDto;
import com.ensa.AgileManager.entities.ProductBacklog;
import com.ensa.AgileManager.entities.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ProductBacklogMapper.class})
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(target = "productOwnerId", source = "productOwner.id")
    @Mapping(target = "productBacklogData", source = "productBacklog")
    ProjectDto toDto(Project project);

    @Mapping(target = "productOwner", ignore = true)
    @Mapping(target = "productBacklog", ignore = true)
    @Mapping(target = "sprints", ignore = true)
    Project toEntity(ProjectDto projectDto);
}
