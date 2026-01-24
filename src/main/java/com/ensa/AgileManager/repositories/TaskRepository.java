package com.ensa.AgileManager.repositories;

import com.ensa.AgileManager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
