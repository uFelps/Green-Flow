package br.com.fiap.model;

import java.sql.Date;

public class Projeto {
	private Long id;
	private String nome;
	private String descricao;
	private Date publicadoEm;
	private String status;
	private String imgUrl;
	
	public Projeto() {
		
	}
	
	public Projeto(Long id, String nome, String descricao, Date publicadoEm, String status, String imgUrl) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.publicadoEm = publicadoEm;
		this.status = status;
		this.imgUrl = imgUrl;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getPublicadoEm() {
		return publicadoEm;
	}

	public void setPublicadoEm(Date publicadoEm) {
		this.publicadoEm = publicadoEm;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
	
}
