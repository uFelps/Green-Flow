package br.com.fiap.model;

public class Parceiro {

	private Long id;
	private String nome;
	private String tipoOrganizacao;
	private String email;
	private String telefone;
	private String imgUrl;

	public Parceiro() {
		super();
	}

	public Parceiro(Long id, String nome, String tipoOrganizacao, String email, String telefone, String imgUrl) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipoOrganizacao = tipoOrganizacao;
		this.email = email;
		this.telefone = telefone;
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

	public String getTipoOrganizacao() {
		return tipoOrganizacao;
	}

	public void setTipoOrganizacao(String tipoOrganizacao) {
		this.tipoOrganizacao = tipoOrganizacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
