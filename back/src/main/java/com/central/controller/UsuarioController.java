package com.central.controller;


import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.central.entity.Usuario;
import com.central.exception.ResourceNotFoundException;
import com.central.repository.UsuarioRepository;
import com.central.service.UsuarioService;


@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuario")
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}
	
	@RequestMapping(path = "usuario/{name}", method = RequestMethod.GET)
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
	
	@PostMapping("/usuario")
	public Usuario registraUsuario(@Valid @RequestBody Usuario usuario) {	
		System.out.println("Novo usuario: " + usuario.getName()); 
		return usuarioRepository.save(usuario);

	}
	
	@PutMapping("/usuario/{id}")
    public ResponseEntity<Usuario> atualizaUsuario(@PathVariable(value = "id") Long usuarioId,
         @Valid @RequestBody Usuario usuarioDetalhes) throws ResourceNotFoundException {
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado para o id " + usuarioId));
        usuario.setName(usuarioDetalhes.getName());
        usuario.setPassword(usuarioDetalhes.getPassword());
        usuario.setEmail(usuarioDetalhes.getEmail());
        final Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/usuario/{id}")
    public Map<String, Boolean> deletaUsuario(@PathVariable(value = "id") Long usuarioId)
         throws ResourceNotFoundException {
    	Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado para o id " + usuarioId));
    	usuarioRepository.delete(usuario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

