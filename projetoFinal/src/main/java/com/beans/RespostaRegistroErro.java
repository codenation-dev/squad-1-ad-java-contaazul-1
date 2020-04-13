package com.beans;

import java.time.Instant;

public class RespostaRegistroErro {

	private int id;
	private String titulo;
	private String detalhes;
	private String origem;
	private Instant data;
	private String nivel;
	private Integer idUsuario;
	private String status;

	
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