package com.central.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.central.entity.Erro;
import com.central.entity.Usuario;
import com.central.repository.UsuarioRepository;
import com.central.service.interfaces.UsuarioServiceInterface;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService implements UsuarioServiceInterface{
	
    @Autowired
    PasswordEncoder encoder;

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

	public String alterarSenha(String email, String senhaAntiga, String senhaNova1, String senhaNova2) {
		if(!repository.findEmail(email).equals(email)) return "Email nao cadastrado";
		if(!senhaNova1.equals(senhaNova2)) return "Senhas incompatíveis";
		if (!encoder.matches(senhaAntiga, repository.findSenha(email))) return "Senha antiga incorreta";
		String senhaNovaCrip = encoder.encode(senhaNova1);
		
		Usuario usuario = repository.findByEmail(email).get();
		usuario.setPassword(senhaNovaCrip);
		repository.save(usuario);
		
		return "Senha atualizada" + senhaNovaCrip;
		
	}

}
