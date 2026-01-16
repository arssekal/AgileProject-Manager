package com.arssekal.AgileManager.controllers;

import com.arssekal.AgileManager.dtos.AuthResponseDto;
import com.arssekal.AgileManager.dtos.ErrorResponseDto;
import com.arssekal.AgileManager.dtos.UserDto;
import com.arssekal.AgileManager.entities.ProductOwner;
import com.arssekal.AgileManager.entities.ScrumMaster;
import com.arssekal.AgileManager.entities.User;
import com.arssekal.AgileManager.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody UserDto user) {
        return userService.saveUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto user) {
        AuthResponseDto responseDto = userService.loginLogic(user);

        if (responseDto != null) {
            return ResponseEntity.ok(responseDto);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        ErrorResponseDto.builder()
                                .status(401)
                                .error("UNAUTHORIZED")
                                .message("email ou password incorrect")
                                .build()
                );
    }

    @GetMapping("/scrum-master/all")
    public List<ScrumMaster> getAllScrumMasters() {
        // this returned somme unused values
        return  userService.getAllScrumMasters();
    }

    @GetMapping("/product-owner/all")
    public List<ProductOwner> getAllProductOwners() {
        // this returned somme unused values
        return  userService.getAllProductOwners();
    }
}
