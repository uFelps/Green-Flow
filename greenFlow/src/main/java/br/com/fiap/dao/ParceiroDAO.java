package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.conexoes.ConnectionFactory;
import br.com.fiap.model.Parceiro;

public class ParceiroDAO {

	// DATA ACCESS OBJECT
	public Connection minhaConexao;

	public ParceiroDAO() throws ClassNotFoundException, SQLException {
		super();
		this.minhaConexao = new ConnectionFactory().conexao();
	}

	// Insert
	public String inserir(Parceiro parceiro) throws SQLException {

		PreparedStatement stmt = minhaConexao.prepareStatement("Insert into Parceiro values (?, ?, ?, ?, ?, ?)");

		stmt.setLong(1, parceiro.getId());
		stmt.setString(2, parceiro.getNome());
		stmt.setString(3, parceiro.getTipoOrganizacao());
		stmt.setString(4, parceiro.getEmail());
		stmt.setString(5, parceiro.getTelefone());
		stmt.setString(6, parceiro.getImgUrl());

		stmt.execute();
		stmt.close();
		return "Parceiro cadastrado com Sucesso!";
	}

	public Parceiro buscarParceiroPorId(Long id) throws SQLException {

		PreparedStatement stmt = minhaConexao.prepareStatement("SELECT * FROM Parceiro WHERE id = ?");
		stmt.setLong(1, id);
		ResultSet resposta = stmt.executeQuery();

		while (resposta.next()) {
			Parceiro parceiro = new Parceiro();
			parceiro.setId(resposta.getLong(1));
			parceiro.setNome(resposta.getString(2));
			parceiro.setTipoOrganizacao(resposta.getString(3));
			parceiro.setEmail(resposta.getString(4));
			parceiro.setTelefone(resposta.getString(5));
			parceiro.setImgUrl(resposta.getString(6));

	
			return parceiro;
		}

		return null;

	}

	public List<Parceiro> buscarTodosParceiros() throws SQLException {

		List<Parceiro> parceiros = new ArrayList<Parceiro>();

		PreparedStatement stmt = minhaConexao.prepareStatement("SELECT * FROM Parceiro");

		ResultSet resposta = stmt.executeQuery();

		while (resposta.next()) {
			Parceiro parceiro = new Parceiro();
			parceiro.setId(resposta.getLong(1));
			parceiro.setNome(resposta.getString(2));
			parceiro.setTipoOrganizacao(resposta.getString(3));
			parceiro.setEmail(resposta.getString(4));
			parceiro.setTelefone(resposta.getString(5));
			parceiro.setImgUrl(resposta.getString(6));
			parceiros.add(parceiro);
		}

		return parceiros;
	}

	public String atualizarParceiro(Parceiro parceiro) throws SQLException {

		PreparedStatement stmt = minhaConexao
				.prepareStatement("Update Parceiro SET nome = ?, tipoOrganizacao = ?, email = ?, telefone = ?, imgUrl = ? where id = ?");
		stmt.setString(1, parceiro.getNome());
		stmt.setString(2, parceiro.getTipoOrganizacao());
		stmt.setString(3, parceiro.getEmail());
		stmt.setString(4, parceiro.getTelefone());
		stmt.setString(5, parceiro.getImgUrl());
		stmt.setLong(6, parceiro.getId());
		stmt.executeUpdate();
		stmt.close();
		
		return "Parceiro atualizado com Sucesso!";

	}

	public String deletarParceiro(long id) throws SQLException {

		PreparedStatement stmt = minhaConexao.prepareStatement("Delete from Parceiro where id = ?");
		stmt.setLong(1, id);
		stmt.execute();
		stmt.close();

		return "Parceiro deletado com Sucesso!";
	}
}
