package com.arssekal.AgileManager.services;

import com.arssekal.AgileManager.entities.User;
import com.arssekal.AgileManager.repositories.UserRepository;
import com.arssekal.AgileManager.services.implementations.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

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
