package com.ensa.AgileManager.repositories;

import com.ensa.AgileManager.entities.ProductBacklog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBacklogRepository extends JpaRepository<ProductBacklog, Long> {
}
