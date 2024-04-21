package org.ryangosling.backend.mapper;

import lombok.experimental.UtilityClass;
import org.ryangosling.backend.controller.dto.*;
import org.ryangosling.backend.domain.User;

@UtilityClass
public class UserMapper {
    public User toUserEntity(UserProfileDto userProfileDto) {
        return User.builder()
                .id(userProfileDto.getId())
                .name(userProfileDto.getName())
                .username(userProfileDto.getUsername())
                .email(userProfileDto.getEmail())
                .phone(userProfileDto.getPhone())
                .build();
    }

    public User toUserEntity(UserRegisterDto userRegisterDto) {
        User user = User.builder()
                .username(userRegisterDto.getUsername())
                .password(userRegisterDto.getPassword())
                .email(userRegisterDto.getEmail())
                .build();

        if (userRegisterDto.getId() != null) user.setId(userRegisterDto.getId());

        return user;
    }

    public UserRegisterDto toUserRegisterDto(User user) {
        return UserRegisterDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    public UserProfileDto toUserProfileDto(User user) {
        return UserProfileDto.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
}