package com.dh.msusers.service;

import com.dh.msusers.model.Bill;
import com.dh.msusers.model.User;
import com.dh.msusers.repository.BillRepository;
import com.dh.msusers.repository.IUserRepository;
import com.dh.msusers.repository.KeycloakUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private IUserRepository userRepository;
    private BillRepository billRepository;

    public UserService(IUserRepository userRepository, KeycloakUserRepository keycloakUserRepository, BillRepository billRepository) {
        this.userRepository = userRepository;
        this.billRepository = billRepository;
    }

    public Optional<User> findById(String id) {
        Optional<User>user = userRepository.findById(id);
        List<Bill> bills = billRepository.findByUserId(id);
        return Optional.of(new User(user.get().getId(), user.get().getUserName(), user.get().getEmail(), user.get().getFirstName(), user.get().getLastName(), bills));
    }
}
