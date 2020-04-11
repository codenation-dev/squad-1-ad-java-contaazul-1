package com.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import com.entity.*;


@RepositoryRestResource
public interface ErroRepository extends CrudRepository<Erro, Integer> {
}
