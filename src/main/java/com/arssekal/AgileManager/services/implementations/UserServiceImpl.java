package com.arssekal.AgileManager.services.implementations;

import com.arssekal.AgileManager.entities.ProductOwner;
import com.arssekal.AgileManager.entities.User;
import com.arssekal.AgileManager.repositories.UserRepository;
import com.arssekal.AgileManager.services.interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public ProductOwner getUser(Long productOwnerId) {
        return (ProductOwner) userRepository.findById(productOwnerId).orElseThrow(() -> new RuntimeException("product owner id not found"));
    }
}
