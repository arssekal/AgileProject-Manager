package com.ensa.AgileManager.services.interfaces;

import com.ensa.AgileManager.dtos.AuthResponseDto;
import com.ensa.AgileManager.dtos.UserDto;
import com.ensa.AgileManager.entities.Developer;
import com.ensa.AgileManager.entities.ProductOwner;
import com.ensa.AgileManager.entities.ScrumMaster;
import com.ensa.AgileManager.entities.User;

import java.util.List;

public interface UserService {
    User getUser(Long productOwnerId);

    User saveUser(UserDto user);

    List<ScrumMaster> getAllScrumMasters();

    List<ProductOwner> getAllProductOwners();

    AuthResponseDto loginLogic(UserDto user);
}
