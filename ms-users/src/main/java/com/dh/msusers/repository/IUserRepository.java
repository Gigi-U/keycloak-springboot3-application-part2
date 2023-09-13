package com.dh.msusers.repository;

import com.dh.msusers.model.User;

import java.util.Optional;

public interface IUserRepository {

  Optional<User> findById(String id);

}
