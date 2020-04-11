package com.controllersREST;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beans.RegistroUsuario;
import com.entity.Usuario;


@Controller
public class ControllerUsuarioGet {
	@RequestMapping(method = RequestMethod.GET, value = "/usuario/get")
	@ResponseBody
	public List<Usuario> getAllUsuarios() {
		return RegistroUsuario.getInstance().getUsuarios();
	}
}