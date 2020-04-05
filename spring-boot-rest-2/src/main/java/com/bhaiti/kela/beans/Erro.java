package com.bhaiti.kela.beans;

import java.util.concurrent.atomic.AtomicInteger;

public class Erro {

	private static final AtomicInteger count = new AtomicInteger(0); 
	private int id;
	private String descricao;
	private String origem;
	private String data;
	private String nivel;
	
	public Erro (String descricao, String origem, String data, String nivel) {
		this.descricao = descricao;
		this.origem = origem;
		this.data = data;
		this.nivel = nivel;
		this.id = count.incrementAndGet();
		
	}
	
	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;

	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;

	}
	
	
	public String getOrigem() {
		return origem;

	}

	public void setOrigem(String origem) {
		this.origem = origem;

	}

	public String getData() {
		return data;

	}

	public void setData(String data) {
		this.data = data;

	}
	
	public String getNivel() {
		return nivel;

	}

	public void setNivel(String nivel) {
		this.nivel = nivel;

	}
	


}