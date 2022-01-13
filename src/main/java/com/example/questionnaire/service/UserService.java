package com.example.questionnaire.service;

import com.example.questionnaire.dao.UserRepository;
import com.example.questionnaire.domain.UserEntity;
import com.example.questionnaire.domain.UserRole;
import com.example.questionnaire.exception.UserNotExistException;
import org.apache.catalina.User;
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

    public boolean userByIdIsPresent(Long id) {
        return userRepository.findById(id).isPresent();
    }

    public UserEntity getUserById(Long id) throws UserNotExistException {
        UserEntity user;

        if (userRepository.findById(id).isPresent()) {
            user = userRepository.findById(id).get();
        } else {
            throw new UserNotExistException("Пользователя с таким id не существует");
        }
        return user;
    }
}
