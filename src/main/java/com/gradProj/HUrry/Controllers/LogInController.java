package com.gradProj.HUrry.Controllers;

import com.gradProj.HUrry.Dto.UserLoginDto;
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
@RequestMapping("/api2")
public class LogInController {

    private static final Logger logger = LoggerFactory.getLogger(LogInController.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}") // Use the same secret key as in the filter
    private String SECRET_KEY;

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

        // Validate role (as an enum)
        User.Role role = user.getRole();
        if (role == null) {
            logger.error("Login failed: User role is not defined for email {}", userLoginDto.getEmail());
            return ResponseEntity.status(500).body(createErrorResponse("User role not defined"));
        }
        // Check if the role is valid (enum validation)
        if (!isRoleValid(role)) {
            logger.error("Login failed: Invalid role '{}' for user {}", role, userLoginDto.getEmail());
            return ResponseEntity.status(500).body(createErrorResponse("Invalid user role"));
        }
        // Generate token (assuming this method exists in your setup)
        String token = generateToken(user);

        // Prepare successful response
        Map<String, Object> response = new HashMap<>();
        response.put("role", role);
        response.put("token", token);
        response.put("user", user);

        logger.info("User {} logged in successfully with role {}", user.getEmail(), role);
        return ResponseEntity.ok(response);
    }

    // Utility method to validate if the `role` matches an allowed Role
    private boolean isRoleValid(User.Role role) {
        return role == User.Role.STUDENT || role == User.Role.OPERATOR || role == User.Role.DRIVER;
    }

    // Utility method to generate a standardized error response
    private Map<String, String> createErrorResponse(String message) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        return errorResponse;
    }

    // Stub method for token generation (to be implemented if not existing)
    private String generateToken(User user) {
        // Your logic for generating JWT or authentication token should go here
        return "generated_token_placeholder";
    }
}
