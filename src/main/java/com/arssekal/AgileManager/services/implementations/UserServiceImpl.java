package com.arssekal.AgileManager.services.implementations;

import com.arssekal.AgileManager.entities.Developer;
import com.arssekal.AgileManager.entities.ProductOwner;
import com.arssekal.AgileManager.entities.ScrumMaster;
import com.arssekal.AgileManager.entities.User;
import com.arssekal.AgileManager.exceptions.UserNotFoundException;
import com.arssekal.AgileManager.repositories.UserRepository;
import com.arssekal.AgileManager.services.interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(Long userId) {
        return  userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public void createProductOwner(ProductOwner productOwner) {
        userRepository.save(productOwner);
    }

    @Override
    public void createScrumMaster(ScrumMaster scrumMaster) {
        userRepository.save(scrumMaster);
    }

    @Override
    public void createDeveloper(Developer developer) {
        userRepository.save(developer);
    }
}
