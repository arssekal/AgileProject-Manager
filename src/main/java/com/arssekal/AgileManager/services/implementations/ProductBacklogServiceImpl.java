package com.arssekal.AgileManager.services.implementations;

import com.arssekal.AgileManager.dtos.EpicDto;
import com.arssekal.AgileManager.dtos.ProductBacklogDto;
import com.arssekal.AgileManager.dtos.UserStoryDto;
import com.arssekal.AgileManager.entities.Epic;
import com.arssekal.AgileManager.entities.ProductBacklog;
import com.arssekal.AgileManager.exceptions.ProductBacklogNotFoundException;
import com.arssekal.AgileManager.mappers.Mapper;
import com.arssekal.AgileManager.repositories.EpicRepository;
import com.arssekal.AgileManager.repositories.ProductBacklogRepository;
import com.arssekal.AgileManager.services.interfaces.EpicService;
import com.arssekal.AgileManager.services.interfaces.ProductBacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductBacklogServiceImpl implements ProductBacklogService {
    @Autowired
    private ProductBacklogRepository productBacklogRepository;
    @Autowired
    private EpicRepository epicRepository;
    @Autowired
    private EpicService epicService;

    @Override
    public ProductBacklogDto getProductBacklog(Long id) {
        ProductBacklog productBacklog = productBacklog(id);
        return Mapper.mapToProductBacklogDto(productBacklog);
    }

    @Override
    public EpicDto addEpic(Long backlogId, EpicDto epicDto) {
        ProductBacklog productBacklog = productBacklog(backlogId);
        Epic epic = Mapper.mapToEpic(epicDto);
        epic.setProductBacklog(productBacklog);
        Epic savedEpic = epicRepository.save(epic);
        return Mapper.mapToEpicDto(savedEpic);
    }

    @Override
    public List<EpicDto> getProductBacklogEpics(Long backlogId) {
        ProductBacklog productBacklog = productBacklog(backlogId);
        return productBacklog.getEpics().stream().map(
                (epic -> Mapper.mapToEpicDto(epic))
        ).toList();
    }

    @Override
    public List<UserStoryDto> getProductBacklogUserStories(Long backlogId) {
        ProductBacklog productBacklog = productBacklog(backlogId);
        List<UserStoryDto> userStoryDtos = new ArrayList<>();
        productBacklog.getEpics().forEach((epic -> {
           userStoryDtos.addAll(epicService.getAllEpicUserStories(epic.getId()));
        }));
        return userStoryDtos;
    }

    private ProductBacklog productBacklog(Long id) {
        return productBacklogRepository.findById(id).orElseThrow(() -> new ProductBacklogNotFoundException(id));
    }
}
