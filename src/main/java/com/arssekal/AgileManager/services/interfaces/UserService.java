package com.arssekal.AgileManager.services.interfaces;

import com.arssekal.AgileManager.dtos.AuthResponseDto;
import com.arssekal.AgileManager.dtos.UserDto;
import com.arssekal.AgileManager.entities.Developer;
import com.arssekal.AgileManager.entities.ProductOwner;
import com.arssekal.AgileManager.entities.ScrumMaster;
import com.arssekal.AgileManager.entities.User;

import java.util.List;

public interface UserService {
    User getUser(Long productOwnerId);

    User saveUser(UserDto user);

    List<ScrumMaster> getAllScrumMasters();

    List<ProductOwner> getAllProductOwners();

    AuthResponseDto loginLogic(UserDto user);
}
