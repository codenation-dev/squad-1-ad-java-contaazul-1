package com.example.demo.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "erro")
public class Erro extends AuditModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "titulo")
	@NotBlank
	@Size(min = 1, max = 100)
	private String titulo;
	
	@Column(name = "detalhes")
	@NotBlank
	@Size(min = 1, max = 100)
	private String detalhes;
	
	@Column(name = "origem")
	@NotBlank
	@Size(min = 1, max = 100)
	private String origem;
	
	@Column(name = "nivel")
	@NotBlank
	@Size(min = 1, max = 100)
	private String nivel;
	
	@Column(name = "id_usuario")
	@NotNull
	private Integer idUsuario;
	
	public Erro() {
		
	}
	
	public Erro (String titulo, String detalhes, String origem, String nivel, Integer idUsuario) {
		this.setTitulo(titulo);
		this.setDetalhes(detalhes);
		this.setOrigem(origem);
		this.setNivel(nivel);
		this.setIdUsuario(idUsuario);
	}
	
	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;

	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;

	}
	
	
	public String getOrigem() {
		return origem;

	}

	public void setOrigem(String origem) {
		this.origem = origem;

	}

	
	public String getNivel() {
		return nivel;

	}

	public void setNivel(String nivel) {
		this.nivel = nivel;

	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}