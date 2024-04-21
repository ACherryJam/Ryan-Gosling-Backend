package org.ryangosling.backend.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.ryangosling.backend.controller.dto.UserProfileDto;
import org.ryangosling.backend.controller.dto.UserRegisterDto;
import org.ryangosling.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public UserProfileDto add(@RequestBody UserRegisterDto user) {
        return userService.add(user);
    }

    @GetMapping("/user")
    public List<UserProfileDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public UserProfileDto getById(@PathVariable long id) {
        return userService.getById(id);
    }

    @PutMapping("/user/{id}")
    public UserProfileDto update(@PathVariable long id, @RequestBody UserProfileDto user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteById(@PathVariable long id) {
        userService.deleteById(id);
    }

    @GetMapping("/user/username/{username}")
    public String getByUsername(@PathVariable String username) {
        UserProfileDto dto = userService.getByUsername(username);

        return "User " + dto.getUsername() + " is registered";
    }

    @GetMapping("/login")
    public UserProfileDto login(Authentication auth) {
        return userService.getByUsername(auth.name());
    }
}