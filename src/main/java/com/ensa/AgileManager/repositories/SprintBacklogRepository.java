package com.ensa.AgileManager.repositories;

import com.ensa.AgileManager.entities.SprintBacklog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintBacklogRepository extends JpaRepository<SprintBacklog, Long> {
}
