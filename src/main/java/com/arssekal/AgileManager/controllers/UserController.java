package com.arssekal.AgileManager.controllers;

import com.arssekal.AgileManager.entities.ProductOwner;
import com.arssekal.AgileManager.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public void createUser(@RequestBody ProductOwner user) {
        userService.saveUser(user);
    }
}
