package com.gradProj.HUrry.Services;
import com.gradProj.HUrry.Dto.UserSignupDto;
import com.gradProj.HUrry.Repositories.UserRepository;
import com.gradProj.HUrry.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void registerUser(UserSignupDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = convertToEntity(userDto);
        userRepository.save(user);
    }

    private User convertToEntity(UserSignupDto signupDto) {
        return modelMapper.map(signupDto, User.class);
    }

}
