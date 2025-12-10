package com.arssekal.AgileManager.repositories;

import com.arssekal.AgileManager.entities.SprintBacklog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintBacklogRepository extends JpaRepository<SprintBacklog, Long> {
}
