package com.arssekal.AgileManager.services.interfaces;

import com.arssekal.AgileManager.entities.Developer;
import com.arssekal.AgileManager.entities.ProductOwner;
import com.arssekal.AgileManager.entities.ScrumMaster;
import com.arssekal.AgileManager.entities.User;

public interface UserService {
    User getUser(Long productOwnerId);

    void createProductOwner(ProductOwner productOwner);

    void createScrumMaster(ScrumMaster scrumMaster);

    void createDeveloper(Developer developer);
}
