package com.central.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.central.entity.Erro;

public interface ErroServiceInterface extends ServiceInterface<Erro>{
	
	Optional<Erro> findById(Long id);

	Integer frequenciaErros(String erro);
	
	List<Erro> findByDescricao(String descricao);
	
	List<Erro> findByOrigem(String origem);
	
	List<Erro> findByLevel(String level);
	
	List<Erro> findArquivados();
	
	List<Erro> findNaoArquivados();

	List<Erro> getAllErros();

}
