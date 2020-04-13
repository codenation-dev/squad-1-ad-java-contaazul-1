package com.example.demo.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.ErroRepository;
import com.example.demo.model.Erro;



@RestController
public class ControllerErro {
	
	@Autowired
	private ErroRepository erroRepository;
	
	@GetMapping("/erro/get")
	public Iterable<Erro> getAllErros() {
		return erroRepository.findAll();
	}
	
	@PostMapping("/erro/post")
	public Erro registraErro(@Valid @RequestBody Erro erro) {	
		System.out.println("Novo erro: " + erro.getTitulo()); 
		return erroRepository.save(erro);

	}
}

