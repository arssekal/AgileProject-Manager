package com.ensa.AgileManager.repositories;

import com.ensa.AgileManager.entities.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStoryRepository extends JpaRepository<UserStory, Long> {
}
