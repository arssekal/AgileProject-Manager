package com.arssekal.AgileManager.controllers;

import com.arssekal.AgileManager.entities.Developer;
import com.arssekal.AgileManager.entities.ProductOwner;
import com.arssekal.AgileManager.entities.ScrumMaster;
import com.arssekal.AgileManager.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/product-owner")
    public void createProductOwner(@RequestBody ProductOwner productOwner) {
        userService.createProductOwner(productOwner);
    }
    @PostMapping("/scrum-master")
    public void createScrumMaster(@RequestBody ScrumMaster scrumMaster) {
        userService.createScrumMaster(scrumMaster);
    }
    @PostMapping("/developer")
    public void createDeveloper(@RequestBody Developer developer) {
        userService.createDeveloper(developer);
    }
    @GetMapping
}
