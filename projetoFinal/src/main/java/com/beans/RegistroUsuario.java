package com.beans;

import java.util.ArrayList;

import java.util.List;

import com.entity.Usuario;

public class RegistroUsuario {
	
	private List<Usuario> listaUsuario;
	private static RegistroUsuario reg = null;
	private RegistroUsuario(){
		listaUsuario = new ArrayList<Usuario>();
	}

	public static RegistroUsuario getInstance() {
		if(reg == null) {
			reg = new RegistroUsuario();
			return reg;
		}
		else {
			return reg;
		}
	}

	public void add(Usuario std) {

		listaUsuario.add(std);

	}

	public String atualizaErro(Usuario u1) {
		for(Usuario u2 : listaUsuario)
		{
			if(u1.getId() == u2.getId()) {
				listaUsuario.set(listaUsuario.indexOf(u2), u1);
				return "Update successful";
			}
		}
		return "Update un-successful";

	}

	public String deletaErro(Usuario u1) {
		for(Usuario u2 : listaUsuario)
		{
			if(u1.getId() == u2.getId()) {
				listaUsuario.remove(u2);
				return "Delete successful";
			}
		}
		return "Delete un-successful";
	}

	public List<Usuario> getUsuarios() {
		return listaUsuario;

	}

}