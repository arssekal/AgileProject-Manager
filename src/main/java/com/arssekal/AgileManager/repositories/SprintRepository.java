package com.arssekal.AgileManager.repositories;

import com.arssekal.AgileManager.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, Long> {

}
