package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import com.example.demo.model.Role;
import com.example.demo.model.RoleName;


@RepositoryRestResource
public interface RoleRepository extends CrudRepository<Role, Integer> {
	Optional<Role> findByName(RoleName roleName);
}
