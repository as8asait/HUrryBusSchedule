package com.gradProj.HUrry.Controllers;

import com.gradProj.HUrry.Dto.UserSignupDto;
import com.gradProj.HUrry.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SignInController {

    private final UserService userService;

    @Autowired
    public SignInController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserSignupDto userSignupDTO) {
        userService.registerUser(userSignupDTO);
        return ResponseEntity.ok("User registered successfully!");
    }
//must edit the service to dto instead of user: done
}

