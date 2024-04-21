package org.ryangosling.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.ryangosling.backend.dao.UserRepository;
import org.ryangosling.backend.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(userName);

        if (!optionalUser.isPresent()) throw new UsernameNotFoundException("User not found");

        return optionalUser.get();
    }
}