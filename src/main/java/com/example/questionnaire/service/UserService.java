package com.example.questionnaire.service;

import com.example.questionnaire.dao.UserRepository;
import com.example.questionnaire.domain.UserEntity;
import com.example.questionnaire.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserEntity user) {
        user.setUserRole(UserRole.USER);
        userRepository.save(user);
    }
}
