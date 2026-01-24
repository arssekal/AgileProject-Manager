package com.ensa.AgileManager.controllers;

import com.ensa.AgileManager.dtos.AuthResponseDto;
import com.ensa.AgileManager.dtos.ErrorResponseDto;
import com.ensa.AgileManager.dtos.UserDto;
import com.ensa.AgileManager.entities.ProductOwner;
import com.ensa.AgileManager.entities.ScrumMaster;
import com.ensa.AgileManager.entities.User;
import com.ensa.AgileManager.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

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
