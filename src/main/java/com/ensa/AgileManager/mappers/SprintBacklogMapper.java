package com.ensa.AgileManager.mappers;

import com.ensa.AgileManager.dtos.SprintBacklogDto;
import com.ensa.AgileManager.entities.Sprint;
import com.ensa.AgileManager.entities.SprintBacklog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SprintBacklogMapper {
    SprintBacklogMapper INSTANCE = Mappers.getMapper(SprintBacklogMapper.class);

    @Mapping(target = "scrumMasterId", source = "sprint.scrumMaster.id")
    @Mapping(target = "dateDebut", source = "sprint.dateDebut")
    @Mapping(target = "dateFin", source = "sprint.dateFin")
    SprintBacklogDto toDto(SprintBacklog sprintBacklog);

    @Mapping(target = "userStories", ignore = true)
    @Mapping(target = "sprint", ignore = true)
    SprintBacklog toEntity(SprintBacklogDto sprintBacklogDto);

    @Mapping(target = "id", source = "sprintBacklog.id")
    @Mapping(target = "nom", source = "sprintBacklog.nom")
    @Mapping(target = "description", source = "sprintBacklog.description")
    @Mapping(target = "scrumMasterId", source = "scrumMaster.id")
    @Mapping(target = "dateDebut", source = "dateDebut")
    @Mapping(target = "dateFin", source = "dateFin")
    SprintBacklogDto toDtoFromSprint(Sprint sprint);
}
