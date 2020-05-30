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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.central.entity.Usuario;
import com.central.exception.ResourceNotFoundException;
import com.central.repository.UsuarioRepository;
import com.central.service.UsuarioService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuario/get")
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}
	
	@RequestMapping(path = "/login/get/{name}/{password}", method = RequestMethod.GET)
	public Usuario login(@PathVariable String name, @PathVariable String password) {
		return usuarioRepository.findByLogin(name, password);
	}
	
	@RequestMapping(path = "usuario/get/{name}", method = RequestMethod.GET)
	public List<Usuario> findName(@PathVariable String name) {
		return usuarioRepository.findByName(name);
	}
	
	@RequestMapping(path = "usuario/alterarsenha/{email}/{senhaAntiga}/{senhaNova1}/{senhaNova2}", method = RequestMethod.GET)
	public String alterarSenha(@PathVariable String email, @PathVariable String senhaAntiga, 
			@PathVariable String senhaNova1, @PathVariable String senhaNova2) {
		
		String retorno = usuarioService.alterarSenha(email, senhaAntiga, senhaNova1, senhaNova2);
		
		System.out.println("ALTERAR SENHA RETORNO: "+retorno);
		
		return retorno;
	}
	
	@PostMapping("/usuario/post")
	public Usuario registraUsuario(@Valid @RequestBody Usuario usuario) {	
		System.out.println("Novo usuario: " + usuario.getName()); 
		return usuarioRepository.save(usuario);

	}
	
	@PutMapping("/usuario/put/{id}")
    public ResponseEntity<Usuario> atualizaUsuario(@PathVariable(value = "id") Long usuarioId,
         @Valid @RequestBody Usuario usuarioDetalhes) throws ResourceNotFoundException {
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado para o id " + usuarioId));
        usuario.setName(usuarioDetalhes.getName());
        usuario.setPassword(usuarioDetalhes.getPassword());
        usuario.setEmail(usuarioDetalhes.getEmail());
        final Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/usuario/delete/{id}")
    public Map<String, Boolean> deletaUsuario(@PathVariable(value = "id") Long usuarioId)
         throws ResourceNotFoundException {
    	Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado para o id " + usuarioId));
    	usuarioRepository.delete(usuario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

