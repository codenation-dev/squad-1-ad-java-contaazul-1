package com.central.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.central.entity.Erro;
import com.central.entity.Usuario;
import com.central.exception.ResourceNotFoundException;
import com.central.repository.ErroRepository;
import com.central.service.ErroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
@RestController
public class ErroController {
	
	@Autowired
	private ErroService erroService;
	

	@GetMapping("/erro")
	public List<Erro> getAllErros() {
		return erroService.getAllErros();
	}
	
	@GetMapping("/erro/{id}")
	public Optional<Erro> getErro(@PathVariable Long id) {
		return erroService.findById(id);
	}
	
	@RequestMapping(path = "/erro/eventos/{titulo}", method = RequestMethod.GET)
	public Integer eventosErro(@PathVariable String titulo) {
		return erroService.frequenciaErros(titulo);
	}
	
	@PostMapping("/erro")
	public Erro registraErro(@Valid @RequestBody Erro erro) {	
		System.out.println("Novo erro: " + erro.getTitulo()); 
		return erroService.save(erro);

	}
	
	@PutMapping("/erro/{id}")
    public ResponseEntity<Erro> atualizaErro(@PathVariable(value = "id") Long erroId,
         @Valid @RequestBody Erro novoErro) throws ResourceNotFoundException{
		return erroService.atualizaErro(erroId, novoErro);
    }

    @DeleteMapping("/erro/{id}")
    public Map<String, Boolean> deletaErro(@PathVariable(value = "id") Long erroId)
         throws ResourceNotFoundException {
        return erroService.removeErro(erroId);
    }
    
	@PutMapping("/erro/arquivarErro/{listaErro}")
    public Boolean arquivarErro(@PathVariable(value = "listaErro") List<Long> listaErros
        ) throws ResourceNotFoundException{
		
		return erroService.arquivarErro(listaErros);
    }
	
    @DeleteMapping("/erro/deleteErros/{listaErros}")
    public Boolean deletaErros(@PathVariable(value = "listaErros") List<Long> listaErros)
         throws ResourceNotFoundException {
		
    	return erroService.deletaErros(listaErros);
    }
}

