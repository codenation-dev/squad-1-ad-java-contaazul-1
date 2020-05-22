package com.central.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.central.entity.Usuario;


@RepositoryRestResource
@Repository
@Component
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
    @Query(value = "select u.* from usuario u where name like %:name%", nativeQuery = true)
    List<Usuario> findByName(@Param("name") String name);
    
    @Query(value = "select u.* from usuario u where name = :name and password = :password", nativeQuery = true)
    Usuario findByLogin(@Param("name") String nome, @Param("password") String senha);
    
    @Query(value = "select * from usuario", nativeQuery = true)
    List<Usuario> findAll();
    
    Optional<Usuario> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Query(value = "select u.password from usuario u where u.email = :email", nativeQuery = true)
	String findSenha(String email);

    @Query(value = "select u.email from usuario u where u.email = :email", nativeQuery = true)
	String findEmail(String email);
    
    Optional<Usuario> findByEmail(String email);  
    
}