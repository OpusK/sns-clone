package com.sns.snsclone.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sns.snsclone.controller.request.UserJoinRequest;
import com.sns.snsclone.controller.request.UserLoginRequest;
import com.sns.snsclone.exception.SnsApplicationException;
import com.sns.snsclone.model.User;
import com.sns.snsclone.service.UserService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void SignUp() throws Exception {
        String userName = "userName";
        String password = "password";

        when(userService.join(userName, password)).thenReturn(mock(User.class));

        mockMvc.perform(post("/api/v1/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void SignUp_Already_Exist_Name() throws Exception {
        String userName = "userName";
        String password = "password";

        when(userService.join(userName, password)).thenThrow(new SnsApplicationException());

        mockMvc.perform(post("/api/v1/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))).andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void LogIn() throws Exception {
        String userName = "userName";
        String password = "password";

        when(userService.login()).thenReturn("test_token");

        mockMvc.perform(post("/api/v1/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password)))).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void LogIn_With_Not_Signed_Up_User_Name() throws Exception {
        String userName = "userName";
        String password = "password";

        when(userService.login()).thenThrow(new SnsApplicationException());

        mockMvc.perform(post("/api/v1/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password)))).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void LogIn_With_Invalid_Password() throws Exception {
        String userName = "userName";
        String password = "password";

        when(userService.login()).thenThrow(new SnsApplicationException());

        mockMvc.perform(post("/api/v1/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password)))).andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
