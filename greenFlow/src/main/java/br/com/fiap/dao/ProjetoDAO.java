package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.conexoes.ConnectionFactory;
import br.com.fiap.model.Projeto;

public class ProjetoDAO {

	// DATA ACCESS OBJECT
	public Connection minhaConexao;

	public ProjetoDAO() throws ClassNotFoundException, SQLException {
		super();
		this.minhaConexao = new ConnectionFactory().conexao();
	}

	// Insert
	public String inserir(Projeto projeto) throws SQLException {

		PreparedStatement stmt = minhaConexao.prepareStatement("Insert into Projeto values (?, ?, ?, ?, ?)");

		stmt.setLong(1, projeto.getId());
		stmt.setString(2, projeto.getNome());
		stmt.setString(3, projeto.getDescricao());
		stmt.setDate(4, projeto.getPublicadoEm());
		stmt.setString(5, projeto.getStatus());

		stmt.execute();
		stmt.close();
		return "Cadastrado com Sucesso!";
	}

	public Projeto buscarProjetoPorId(Long id) throws SQLException {

		PreparedStatement stmt = minhaConexao.prepareStatement("SELECT * FROM Projeto WHERE id = ?");
		stmt.setLong(1, id);
		ResultSet resposta = stmt.executeQuery();

		while (resposta.next()) {
			Projeto projeto = new Projeto();
			projeto.setId(resposta.getLong(1));
			projeto.setNome(resposta.getString(2));
			projeto.setDescricao(resposta.getString(3));

			projeto.setPublicadoEm(resposta.getDate(4));

			projeto.setStatus(resposta.getString(5));
			return projeto;
		}

		return null;

	}

	public List<Projeto> buscarTodosProjetos() throws SQLException {

		List<Projeto> projetos = new ArrayList<Projeto>();

		PreparedStatement stmt = minhaConexao.prepareStatement("SELECT * FROM Projeto");

		ResultSet resposta = stmt.executeQuery();

		while (resposta.next()) {
			Projeto projeto = new Projeto();
			projeto.setId(resposta.getLong(1));
			projeto.setNome(resposta.getString(2));
			projeto.setDescricao(resposta.getString(3));

			projeto.setPublicadoEm(resposta.getDate(4));

			projeto.setStatus(resposta.getString(5));
			projetos.add(projeto);
		}

		return projetos;
	}

	public String atualizarProjeto(Projeto projeto) throws SQLException {

		PreparedStatement stmt = minhaConexao
				.prepareStatement("Update Projeto SET nome = ?, descricao = ?, " + "  status = ? where id = ?");
		stmt.setString(1, projeto.getNome());
		stmt.setString(2, projeto.getDescricao());
		stmt.setString(3, projeto.getStatus());
		stmt.setLong(4, projeto.getId());
		stmt.executeUpdate();
		stmt.close();
		
		return "Atualizado com Sucesso!";

	}

	public String deletarProjeto(long id) throws SQLException {

		PreparedStatement stmt = minhaConexao.prepareStatement("Delete from Projeto where id = ?");
		stmt.setLong(1, id);
		stmt.execute();
		stmt.close();

		return "Deletado com Sucesso!";
	}
}
