package com.example.demo.controller;


import java.util.HashMap;
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


import com.example.demo.repository.UsuarioRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Usuario;

@CrossOrigin(origins = "*")
@RestController
public class ControllerUsuario {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/usuario/get")
	public Iterable<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}
	
	@PostMapping("/usuario/post")
	public Usuario registraUsuario(@Valid @RequestBody Usuario usuario) {	
		System.out.println("Novo usuario: " + usuario.getNome()); 
		return usuarioRepository.save(usuario);

	}
	
	@PutMapping("/usuario/put/{id}")
    public ResponseEntity<Usuario> atualizaUsuario(@PathVariable(value = "id") Long usuarioId,
         @Valid @RequestBody Usuario usuarioDetalhes) throws ResourceNotFoundException {
		Usuario usuario = usuarioRepository.findById(usuarioId.intValue()).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado para o id " + usuarioId));

        usuario.setNome(usuarioDetalhes.getNome());
        usuario.setSenha(usuarioDetalhes.getSenha());
        usuario.setEmail(usuarioDetalhes.getEmail());
        //usuario.setToken(usuarioDetalhes.getToken());
        final Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/usuario/delete/{id}")
    public Map<String, Boolean> deletaUsuario(@PathVariable(value = "id") Long usuarioId)
         throws ResourceNotFoundException {
    	Usuario usuario = usuarioRepository.findById(usuarioId.intValue()).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado para o id " + usuarioId));

    	usuarioRepository.delete(usuario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

