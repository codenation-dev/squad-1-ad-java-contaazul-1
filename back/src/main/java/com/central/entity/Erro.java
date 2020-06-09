package com.central.entity;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "erro")
public class Erro extends AuditModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "titulo")
	@NotBlank
	@Size(min = 1, max = 100)
	private String titulo;
	
	@Column(name = "detalhes")
	@NotBlank
	@Size(min = 1, max = 100)
	private String detalhes;
	
	@Column(name = "ambiente")
	@NotBlank
	@Size(min = 1, max = 100)
	private String ambiente;
	
	@Column(name = "origem")
	@NotBlank
	@Size(min = 1, max = 100)
	private String origem;
	
	@Column(name = "nivel")
	@NotBlank
	@Size(min = 1, max = 100)
	private String nivel;
	
	@Column(name = "arquivado")
	@NotNull
	private boolean arquivado;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName="id")
    @NotNull
    private Usuario usuario;
	
	public Erro() {
		
	}

	public Long getId() {
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


	public boolean isArquivado() {
		return arquivado;
	}

	public void setArquivado(boolean arquivado) {
		this.arquivado = arquivado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}


}