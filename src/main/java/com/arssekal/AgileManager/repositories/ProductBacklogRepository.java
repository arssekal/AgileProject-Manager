package com.arssekal.AgileManager.repositories;

import com.arssekal.AgileManager.entities.ProductBacklog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBacklogRepository extends JpaRepository<ProductBacklog, Long> {
}
