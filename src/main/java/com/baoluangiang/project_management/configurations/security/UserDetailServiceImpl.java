package com.baoluangiang.project_management.configurations.security;

import com.baoluangiang.project_management.entities.Information;
import com.baoluangiang.project_management.entities.Phone;
import com.baoluangiang.project_management.entities.User;
import com.baoluangiang.project_management.configurations.security.UserDetailsImpl;
import com.baoluangiang.project_management.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<List<User>> user = userRepository.findUserByUsername(username);
        if (user.isPresent() && !user.get().isEmpty()) {
            return new UserDetailsImpl(user.get().get(0));
        } else {
            throw new UsernameNotFoundException("User with username " + username + " does not exist!");
        }
    }
}
