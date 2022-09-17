package com.sns.snsclone.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    
    @Autowired
    private UserService userService;

    @Test
    void SignUp_Success() {
        String userName = "userName";
        String password = "password";

        Assertions.assertDoesNotThrow(() -> userService.join(userName, password));
    }
}
