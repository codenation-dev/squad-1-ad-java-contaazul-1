package com.central.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.central.entity.Erro;


@RepositoryRestResource
public interface ErroRepository extends CrudRepository<Erro, Long> {
	

    @Query(value = "select count(e.id) from erro e where e.titulo = :titulo", nativeQuery = true)
    Integer frequenciaErros(@Param("titulo") String titulo);
    
    @Query(value = "select e.* from erro e where e.level like %:level%", nativeQuery = true)
    List<Erro> findByLevel(@Param("level") String level);
    
    @Query(value = "select e.* from erro e where e.origem like %:origem%", nativeQuery = true)
    List<Erro> findByOrigem(@Param("origem") String origem);
	
    @Query(value = "select e.* from erro e where e.descricao like %:descricao%", nativeQuery = true)
    List<Erro> findByDescricao(@Param("descricao") String descricao);
    
    @Query(value = "select e.* from erro e where e.arquivado = false", nativeQuery = true)
    List<Erro> findArquivados();
    
    @Query(value = "select e.* from erro e where e.arquivado = true", nativeQuery = true)
    List<Erro> findNaoArquivados();
    
    @Query(value = "select e.* from erro e", nativeQuery = true)
    List<Erro> findAll();
    
    @Query(value = "delete from erro where id = :id", nativeQuery = true)
    void delete(@Param("id") Long id);
    
}
