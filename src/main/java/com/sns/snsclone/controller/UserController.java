package com.sns.snsclone.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.snsclone.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
 
    private final UserService userService;
 
    // TODO: implement
    @PostMapping
    public void join() {
        userService.join("", "");
    }
}
