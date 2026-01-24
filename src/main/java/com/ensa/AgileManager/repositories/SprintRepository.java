package com.ensa.AgileManager.repositories;

import com.ensa.AgileManager.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, Long> {

}
