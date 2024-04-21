package org.ryangosling.backend.service;

import org.ryangosling.backend.controller.dto.UserProfileDto;
import org.ryangosling.backend.controller.dto.UserRegisterDto;
import java.util.List;

public interface UserService {
    UserProfileDto add(UserRegisterDto user);

    List<UserProfileDto> getAll();

    UserProfileDto getById(long id);

    UserProfileDto update(Long id, UserProfileDto userProfileDto);

    void deleteById(long id);

    UserProfileDto getByUsername(String username);
}