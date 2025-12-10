package com.arssekal.AgileManager.repositories;

import com.arssekal.AgileManager.entities.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStoryRepository extends JpaRepository<UserStory, Long> {
}
