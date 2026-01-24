package com.ensa.AgileManager.mappers;

import com.ensa.AgileManager.dtos.ProductBacklogDto;
import com.ensa.AgileManager.entities.ProductBacklog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductBacklogMapper {
    ProductBacklogMapper INSTANCE = Mappers.getMapper(ProductBacklogMapper.class);

    ProductBacklogDto toDto(ProductBacklog productBacklog);

    @Mapping(target = "epics", ignore = true)
    @Mapping(target = "project", ignore = true)
    ProductBacklog toEntity(ProductBacklogDto productBacklogDto);
}
