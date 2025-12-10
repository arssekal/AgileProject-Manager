package com.arssekal.AgileManager.repositories;

import com.arssekal.AgileManager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
