package com.central.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.central.entity.Erro;
import com.central.entity.Usuario;
import com.central.repository.UsuarioRepository;
import com.central.service.interfaces.UsuarioServiceInterface;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService implements UsuarioServiceInterface{

	@Autowired(required=false)
	private UsuarioRepository repository;
	 
	@Override
	public Usuario save(Usuario object) {
		return repository.save(object);
	}
	
	@Override
	public Optional<Usuario> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Usuario> findByName(String name) {
		return repository.findByName(name);
	}
	
	@Override
	public Usuario findByLogin(String name, String senha) {
		return repository.findByLogin(name, senha);
	}

	@Override
	public List<Usuario> getAllUsuarios() {
		return repository.findAll();
	}
	

}
