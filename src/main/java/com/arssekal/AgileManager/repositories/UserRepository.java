package com.arssekal.AgileManager.repositories;

import com.arssekal.AgileManager.entities.ProductOwner;
import com.arssekal.AgileManager.entities.ScrumMaster;
import com.arssekal.AgileManager.entities.User;
import com.arssekal.AgileManager.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
=======

import java.util.List;
import java.util.Optional;
>>>>>>> mybranch

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
<<<<<<< HEAD
=======
    User findByEmailIgnoreCase(String email);

>>>>>>> mybranch
    @Query("SELECT u FROM User u WHERE TYPE(u) = ScrumMaster")
    List<ScrumMaster> findAllScrumMasters();

    @Query("SELECT u FROM User u WHERE TYPE(u) = ProductOwner")
    List<ProductOwner> findAllProductOwners();
}
