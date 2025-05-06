package com.gradProj.HUrry.Controllers;

import com.gradProj.HUrry.Dto.UserLoginDto;
import com.gradProj.HUrry.Services.JwtUtil;
import com.gradProj.HUrry.Services.UserService;
import com.gradProj.HUrry.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LogInController {

    private static final Logger logger = LoggerFactory.getLogger(LogInController.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}") // Use the same secret key as in the filter
    private String SECRET_KEY;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public LogInController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto userLoginDto) {
        User user = userService.getUserByEmail(userLoginDto.getEmail());
        // Retrieve user by email
        if (user == null) {
            logger.warn("Login failed: User not found with email {}", userLoginDto.getEmail());
            return ResponseEntity.status(401).body("User not found");
        }
        // Validate password
        if (!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            logger.warn("Login failed: Invalid password for user {}", userLoginDto.getEmail());
            return ResponseEntity.status(401).body("Invalid password");
        }

        // Generate token (assuming this method exists in your setup)
        String token = generateToken(user);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);


        logger.info("User {} logged in successfully", user.getEmail());
        return ResponseEntity.ok(response);
    }


    // Stub method for token generation (to be implemented if not existing)
    private String generateToken(User user) {
        // Your logic for generating JWT or authentication token should go here

        return jwtUtil.generateToken(user.getEmail(),user.getRole().name());
    }
}
