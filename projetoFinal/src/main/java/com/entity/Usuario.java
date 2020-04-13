package com.entity;

import java.util.concurrent.atomic.AtomicInteger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "usuario")
public class Usuario {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nome;
	private String senha;
	private String token;
	private String email;
	
	public Usuario (String nome, String senha, String token, String email) {
		this.setNome(nome);
		this.setSenha(senha);
		this.setToken(token);
		this.setEmail(email);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	


}