package com.arssekal.AgileManager.services.implementations;

import com.arssekal.AgileManager.dtos.AuthResponseDto;
import com.arssekal.AgileManager.dtos.UserDto;
import com.arssekal.AgileManager.entities.Developer;
import com.arssekal.AgileManager.entities.ProductOwner;
import com.arssekal.AgileManager.entities.ScrumMaster;
import com.arssekal.AgileManager.entities.User;
import com.arssekal.AgileManager.enums.Role;
import com.arssekal.AgileManager.exceptions.UserNotFoundException;
import com.arssekal.AgileManager.mappers.Mapper;
import com.arssekal.AgileManager.repositories.UserRepository;
import com.arssekal.AgileManager.services.JwtService;
import com.arssekal.AgileManager.services.interfaces.UserService;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @Override
    public User getUser(Long userId) {
        return  userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public User saveUser(UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("password after encoding: "+ user.getPassword());
        if(user.getRole() == Role.PRODUCT_OWNER) {
            ProductOwner productOwner = ProductOwner.builder()
                    .nom(user.getNom())
                    .prenom(user.getPrenom())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();
            return userRepository.save(productOwner);
        }
        if(user.getRole() == Role.SCRUM_MASTER) {
            ScrumMaster scrumMaster = ScrumMaster.builder()
                    .nom(user.getNom())
                    .prenom(user.getPrenom())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();
            return userRepository.save(scrumMaster);
        }
        if(user.getRole() == Role.DEVELOPER) {
            Developer developer = Developer.builder()
                    .nom(user.getNom())
                    .prenom(user.getPrenom())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();
            return userRepository.save(developer);
        }
        return userRepository.save(Mapper.mapToUser(user));
    }

    @Override
    public AuthResponseDto loginLogic(UserDto userDto) {
        User user = userRepository.findByEmailIgnoreCase(userDto.getEmail());
        if (user == null) {
            System.out.println("user not found with email: "+ userDto.getEmail());
            return null;
        }
//        user.setPassword(userDto.getPassword());
       try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDto.getEmail(),
                            userDto.getPassword()
                    )
            );
            return AuthResponseDto.builder()
//                    .userId(user.getId())
//                    .nom(user.getNom())
//                    .prenom(user.getPrenom())
//                    .email(user.getEmail())
                    .accessToken(jwtService.generateToken(user.getEmail(), user.getRole()))
                    .user(Mapper.mapToUserDto(user))
                    .build();
        } catch (AuthenticationException e) {
            System.out.println("auth failed: "+ e.getMessage());
        }
        return null;
    }
    @Override
    public List<ScrumMaster> getAllScrumMasters() {
        return userRepository.findAllScrumMasters();
    }

    @Override
    public List<ProductOwner> getAllProductOwners() {
        return userRepository.findAllProductOwners();
    }

}
