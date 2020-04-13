package com.entity;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "erro")
public class Erro {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String titulo;
	private String detalhes;
	private String origem;
	private Instant data;
	private String nivel;
	private Integer idUsuario;
	private String status;
	
	public Erro (String titulo, String detalhes, String origem, Instant data, String nivel, Integer idUsuario) {
		this.setTitulo(titulo);
		this.setDetalhes(detalhes);
		this.setOrigem(origem);
		this.setData(Instant.now());
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

	public Instant getData() {
		return data;

	}

	public void setData(Instant data) {
		this.data = data;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	


}