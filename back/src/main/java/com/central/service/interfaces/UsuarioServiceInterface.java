package com.central.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.central.entity.Usuario;
import com.central.service.interfaces.ServiceInterface;

public interface UsuarioServiceInterface extends ServiceInterface<Usuario>{
	
	Optional<Usuario> findById(Long id);
	
	List<Usuario> findByName(String name);

	Usuario findByLogin(String name, String senha);
	
	List<Usuario> getAllUsuarios();
}
