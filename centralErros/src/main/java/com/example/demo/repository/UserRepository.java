package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import com.example.demo.model.User;


@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Integer> {

Optional<User> findByUsername(String username);
Boolean existsByUsername(String username);
Boolean existsByEmail(String email);
}
