package com.central.repository;


import java.util.List;

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
	
    @Query(value = "select u.* from usuario u where nome like '%:name%'", nativeQuery = true)
    List<Usuario> findByName(@Param("name") String name);
    
    @Query(value = "select u.* from usuario u where nome = :nome and senha = :senha", nativeQuery = true)
    Usuario findByLogin(@Param("nome") String nome, @Param("senha") String senha);
	
}