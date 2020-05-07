package com.central.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.central.entity.Erro;
import com.central.exception.ResourceNotFoundException;
import com.central.repository.ErroRepository;
import com.central.service.ErroService;


//@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ErroController {
	
	@Autowired
	private ErroRepository erroRepository;
	private ErroService erroService;
	

	@GetMapping("/erro/get")
	public List<Erro> getAllErros() {
		return erroService.getAllErros();
	}
	
	@PostMapping("/erro/post")
	public Erro registraErro(@Valid @RequestBody Erro erro) {	
		System.out.println("Novo erro: " + erro.getTitulo()); 
		return erroService.save(erro);

	}
	
	@PutMapping("/erro/put/{id}")
    public ResponseEntity<Erro> atualizaErro(@PathVariable(value = "id") Long erroId,
         @Valid @RequestBody Erro novoErro) throws ResourceNotFoundException{
		return erroService.atualizaErro(erroId, novoErro);
    }

    @DeleteMapping("/erro/delete/{id}")
    public Map<String, Boolean> deletaErro(@PathVariable(value = "id") Long erroId)
         throws ResourceNotFoundException {
        Erro erro = erroRepository.findById(erroId).orElseThrow(() -> new ResourceNotFoundException("Erro não encontrado para o id " + erroId));

        erroRepository.delete(erro);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

