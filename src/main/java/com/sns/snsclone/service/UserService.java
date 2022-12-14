package com.sns.snsclone.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sns.snsclone.exception.SnsApplicationException;
import com.sns.snsclone.model.User;
import com.sns.snsclone.model.entity.UserEntity;
import com.sns.snsclone.repository.UserEntityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    // TODO: implement
    public User join(String userName, String password) {
        // TODO: check if userName duplicated
        Optional<UserEntity> userEntity = userEntityRepository.findByUserName(userName);

        // TODO: signup process -> register user
        userEntityRepository.save(new UserEntity());

        return new User();
    }
    
    // TODO: implement
    public String login(String userName, String password) {
        // check if registered user
        UserEntity userEntity = userEntityRepository.findByUserName(userName).orElseThrow(() -> new SnsApplicationException());

        // check password is valid
        if (!userEntity.getPassword().equals(password)) {
            throw new SnsApplicationException();
        }

        // generate token

        return "";
    }
}
