package com.dh.msusers.service;

import com.dh.msusers.model.BillDTO;
import com.dh.msusers.model.User;
import com.dh.msusers.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private IUserRepository userRepository;
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
//    public User findById(String id) {
//        return userRepository.findById(id);
//    }
}
