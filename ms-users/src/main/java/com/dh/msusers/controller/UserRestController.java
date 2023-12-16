package com.dh.msusers.controller;

import com.dh.msusers.model.User;
import com.dh.msusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private UserService userService;
    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // http://localhost:8090/api/v1/users/find/a5f7117b-f40f-487d-bf4f-f259db296946     provider2
    //http://localhost:8090/api/v1/users/find/d94704ec-c9f5-48f2-9f4a-3e6bd57ad0c9      provider1

    @GetMapping("/find/{id}")
    public ResponseEntity<User> findByCustomerBill(@PathVariable String id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {

            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
