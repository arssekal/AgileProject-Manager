package com.arssekal.AgileManager.repositories;

import com.arssekal.AgileManager.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByDescription(String desc);
    List<Project> findTop3ByOrderByCreatedAtDesc();
}
