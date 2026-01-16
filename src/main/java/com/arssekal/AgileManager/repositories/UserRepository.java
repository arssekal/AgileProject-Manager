package com.arssekal.AgileManager.repositories;

import com.arssekal.AgileManager.entities.ProductOwner;
import com.arssekal.AgileManager.entities.ScrumMaster;
import com.arssekal.AgileManager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIgnoreCase(String email);

    @Query("SELECT u FROM User u WHERE TYPE(u) = ScrumMaster")
    List<ScrumMaster> findAllScrumMasters();

    @Query("SELECT u FROM User u WHERE TYPE(u) = ProductOwner")
    List<ProductOwner> findAllProductOwners();
}
