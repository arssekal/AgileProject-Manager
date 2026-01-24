package com.ensa.AgileManager.services;

import com.ensa.AgileManager.entities.User;
import com.ensa.AgileManager.repositories.UserRepository;
import com.ensa.AgileManager.services.implementations.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIgnoreCase(userEmail);
        if (user == null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found with id: "+ userEmail);
        }
        return new UserDetailsImpl(user);
    }
}
