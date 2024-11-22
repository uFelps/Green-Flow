package br.com.fiap.model;

public class FonteEnergia {

	private Long id;
	private String nome;
	private String tipo;
	private String descricao;
	private Integer impactoAmbiental;
	private String imgUrl;

	public FonteEnergia() {
		super();
	}

	public FonteEnergia(Long id, String nome, String tipo, String descricao, Integer impactoAmbiental, String imgUrl) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.descricao = descricao;
		this.impactoAmbiental = impactoAmbiental;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getImpactoAmbiental() {
		return impactoAmbiental;
	}

	public void setImpactoAmbiental(Integer impactoAmbiental) {
		this.impactoAmbiental = impactoAmbiental;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
