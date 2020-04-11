package com.controllersREST;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beans.*;
import com.entity.*;

@Controller

public class ControllerUsuarioPost {
	@RequestMapping(method = RequestMethod.POST, value = "/usuario/post")
	@ResponseBody

	public RespostaRegistroUsuario registraUsuario(@RequestBody Usuario usuario) {

		System.out.println("In registraErro");
		RespostaRegistroUsuario resposta = new RespostaRegistroUsuario();
		RegistroUsuario.getInstance().add(usuario);

		resposta.setNome(usuario.getNome());
		resposta.setSenha(usuario.getSenha());
		// resposta.setToken(usuario.getToken());
		resposta.setEmail(usuario.getEmail());
		return resposta;

	}

}
