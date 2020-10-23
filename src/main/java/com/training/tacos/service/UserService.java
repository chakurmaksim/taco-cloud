package com.training.tacos.service;

import com.training.tacos.data.model.User;
import com.training.tacos.data.repository.UserRepository;
import com.training.tacos.service.dto.RegistrationForm;
import com.training.tacos.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void saveUser(RegistrationForm registrationForm, PasswordEncoder passwordEncoder) {
        User newUser = userMapper.toUser(registrationForm, passwordEncoder);
        userRepository.save(newUser);
    }
}
