package com.example.demo.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import com.example.demo.model.Usuario;


@RepositoryRestResource
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
}
