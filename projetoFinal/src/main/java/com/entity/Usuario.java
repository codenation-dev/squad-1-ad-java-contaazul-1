package com.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "nome")
	@NotNull
	@Size(min = 1, max = 100)
	private String nome;
	
	@Column(name = "senha")
	@NotNull
	@Size(min = 1, max = 100)
	private String senha;
	
	@Column(name = "token")
	@NotNull
	@Size(min = 1, max = 100)
	private String token;
	
	@Column(name = "email")
	@NotNull
	@Size(min = 1, max = 100)
	private String email;
	
	public Usuario() {
		
	}
	
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