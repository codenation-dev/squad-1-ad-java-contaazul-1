package com.example.demo.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.repository.UsuarioRepository;
import com.example.demo.model.Usuario;

@RestController
public class ControllerUsuario {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/usuario/get")
	public Iterable<Usuario> getAllErros() {
		return usuarioRepository.findAll();
	}
	
	@PostMapping("/usuario/post")
	public Usuario registraUsuario(@Valid @RequestBody Usuario usuario) {	
		System.out.println("Novo usuario: " + usuario.getNome()); 
		return usuarioRepository.save(usuario);

	}
}

