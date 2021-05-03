package com.el_proyecte_grande.imom.users.controller;

import com.el_proyecte_grande.imom.user_diary.model.DiaryDTO;
import com.el_proyecte_grande.imom.users.model.User;
import com.el_proyecte_grande.imom.users.model.UserDTO;
import com.el_proyecte_grande.imom.users.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @ApiOperation("Operation to get user by given user ID")
    @GetMapping("/users/{userId}")
    public UserDTO showUser(@PathVariable("userId") Long userId) {
        log.info("Log Message: ");
        return userService.findByUserId(userId);
    }

    @CrossOrigin
    @ApiOperation("Operation to save new user with given user ID")
    @PostMapping("/users/{userId}")
    public UserDTO saveDiary(@PathVariable("userId") Long userId, @RequestBody UserDTO userDTO) {
        return userService.saveUser(userId, userDTO);
    }
}

