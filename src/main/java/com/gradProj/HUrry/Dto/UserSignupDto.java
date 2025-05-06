package com.gradProj.HUrry.Dto;

import lombok.Data;

@Data
public class UserSignupDto {

    private String username;
    private String email;
    private String password;
    private String role;
    private String phoneNumber;
}
