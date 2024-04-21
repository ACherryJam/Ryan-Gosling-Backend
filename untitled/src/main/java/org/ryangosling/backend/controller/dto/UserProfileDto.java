package org.ryangosling.backend.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private long id;
    private String username;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private String email;
}