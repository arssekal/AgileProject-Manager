package com.arssekal.AgileManager.repositories;

import com.arssekal.AgileManager.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByDescription(String desc);
}
