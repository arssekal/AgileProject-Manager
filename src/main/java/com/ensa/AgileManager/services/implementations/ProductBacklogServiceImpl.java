package com.ensa.AgileManager.services.implementations;

import com.ensa.AgileManager.dtos.EpicDto;
import com.ensa.AgileManager.dtos.ProductBacklogDto;
import com.ensa.AgileManager.dtos.UserStoryDto;
import com.ensa.AgileManager.entities.Epic;
import com.ensa.AgileManager.entities.ProductBacklog;
import com.ensa.AgileManager.exceptions.ProductBacklogNotFoundException;
import com.ensa.AgileManager.mappers.EpicMapper;
import com.ensa.AgileManager.mappers.ProductBacklogMapper;
import com.ensa.AgileManager.repositories.EpicRepository;
import com.ensa.AgileManager.repositories.ProductBacklogRepository;
import com.ensa.AgileManager.services.interfaces.EpicService;
import com.ensa.AgileManager.services.interfaces.ProductBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductBacklogServiceImpl implements ProductBacklogService {
    private final ProductBacklogRepository productBacklogRepository;
    private final EpicRepository epicRepository;
    private final EpicService epicService;

    @Override
    public ProductBacklogDto getProductBacklog(Long id) {
        ProductBacklog productBacklog = productBacklog(id);
        return ProductBacklogMapper.INSTANCE.toDto(productBacklog);
    }

    @Override
    public EpicDto addEpic(Long backlogId, EpicDto epicDto) {
        ProductBacklog productBacklog = productBacklog(backlogId);
        Epic epic = EpicMapper.INSTANCE.toEntity(epicDto);
        epic.setProductBacklog(productBacklog);
        Epic savedEpic = epicRepository.save(epic);
        return EpicMapper.INSTANCE.toDto(savedEpic);
    }

    @Override
    public List<EpicDto> getProductBacklogEpics(Long backlogId) {
        ProductBacklog productBacklog = productBacklog(backlogId);
        return productBacklog.getEpics().stream().map(
                (epic -> EpicMapper.INSTANCE.toDto(epic))
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

    @Override
    public Long userStoriesOverView(Long backlogId) {
        return 0L;
    }

    private ProductBacklog productBacklog(Long id) {
        return productBacklogRepository.findById(id).orElseThrow(() -> new ProductBacklogNotFoundException(id));
    }
}
