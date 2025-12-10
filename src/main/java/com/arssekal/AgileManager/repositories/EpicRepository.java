package com.arssekal.AgileManager.repositories;

import com.arssekal.AgileManager.entities.Epic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpicRepository extends JpaRepository<Epic, Long> {
    List<Epic> findByTitre(String titre);
}
