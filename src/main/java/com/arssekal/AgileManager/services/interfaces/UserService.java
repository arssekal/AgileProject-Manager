package com.arssekal.AgileManager.services.interfaces;

import com.arssekal.AgileManager.entities.ProductOwner;
import com.arssekal.AgileManager.entities.User;

public interface UserService {
    void saveUser(User user);

    ProductOwner getUser(Long productOwnerId);
}
