package com.dh.msusers.controller;

import com.dh.msusers.model.User;
import com.dh.msusers.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    // http://localhost:8090/api/v1/users/find/0d374cd6-4f92-481c-9441-05e79b22740b
    @GetMapping("/find/{id}")
    public ResponseEntity<User> findUserById(@PathVariable String id) {

        Optional<User> findUser = userService.findById(id);
        if (findUser.isPresent()) {
            return ResponseEntity.ok(findUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
