package com.central.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "usuario")
public class Usuario extends AuditModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nome")
	@NotBlank
	@Size(min = 1, max = 100)
	private String nome;
	
	@Column(name = "senha")
	@NotBlank
	@Size(min = 1, max = 100)
	private String senha;
	
	@Column(name = "email")
	@NotBlank
	@Size(min = 1, max = 100)
	@Email
	private String email;
		
	public Usuario() {
		
	}
	
	public Usuario (String nome, String senha, String email) {
		this.setNome(nome);
		this.setSenha(senha);
		this.setEmail(email);
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	


}