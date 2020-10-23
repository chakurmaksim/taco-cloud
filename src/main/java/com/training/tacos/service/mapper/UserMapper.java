package com.training.tacos.service.mapper;

import com.training.tacos.data.model.User;
import com.training.tacos.service.dto.RegistrationForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User toUser(RegistrationForm registrationForm, PasswordEncoder passwordEncoder) {
        User user = modelMapper.map(registrationForm, User.class);
        user.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
        return user;
    }
}
