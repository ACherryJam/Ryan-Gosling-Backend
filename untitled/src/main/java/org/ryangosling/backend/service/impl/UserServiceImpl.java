package org.ryangosling.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.ryangosling.backend.controller.dto.UserProfileDto;
import org.ryangosling.backend.controller.dto.UserRegisterDto;
import org.ryangosling.backend.dao.AuthorityRepository;
import org.ryangosling.backend.dao.UserRepository;
import org.ryangosling.backend.domain.Authority;
import org.ryangosling.backend.domain.User;
import org.ryangosling.backend.mapper.UserMapper;
import org.ryangosling.backend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserProfileDto add(UserRegisterDto userRegisterDto) {
        if (userRepository.findByUsername(userRegisterDto.getUsername()).isPresent())
            throw new RuntimeException("User already exists");

        Optional<Authority> optionalAuthority = authorityRepository.findByAuthority("ROLE_USER");
        if (!optionalAuthority.isPresent())
            throw new RuntimeException("Authority not found");

        Set<Authority> authoritySet = new HashSet<>();
        authoritySet.add(optionalAuthority.get());

        User user = UserMapper.toUserEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setAuthorities(authoritySet);

        User savedUser = userRepository.save(user);
        return UserMapper.toUserProfileDto(savedUser);
    }

    @Override
    public List<UserProfileDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserProfileDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserProfileDto getById(long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) throw new RuntimeException("User with ID " + id + " not found");

        return UserMapper.toUserProfileDto(userOptional.get());
    }

    @Override
    public UserProfileDto update(Long id, UserProfileDto userProfileDto) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) throw new RuntimeException("User with ID " + id + " not found");

        User user = userOptional.get();
        if (userProfileDto.getName() != null) user.setName(userProfileDto.getName());
        if (userProfileDto.getSurname() != null) user.setSurname(userProfileDto.getSurname());
        if (userProfileDto.getUsername() != null) user.setUsername(userProfileDto.getUsername());
        if (userProfileDto.getEmail() != null) user.setEmail(userProfileDto.getEmail());
        if (userProfileDto.getPhone() != null) user.setPhone(userProfileDto.getPhone());
        if (userProfileDto.getAddress() != null) user.setAddress(userProfileDto.getAddress());

        return UserMapper.toUserProfileDto(userRepository.save(user));
    }


    @Override
    public void deleteById(long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent())
            throw new RuntimeException("User with id " + id + " not found");
        userRepository.deleteById(id);
    }

    @Override
    public UserProfileDto getByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (!userOptional.isPresent())
            throw new RuntimeException("User with username " + username + " not found");
        return UserMapper.toUserProfileDto(userOptional.get());
    }
}
