package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.conexoes.ConnectionFactory;
import br.com.fiap.model.FonteEnergia;

public class FonteEnergiaDAO {

	// DATA ACCESS OBJECT
	public Connection minhaConexao;

	public FonteEnergiaDAO() throws ClassNotFoundException, SQLException {
		super();
		this.minhaConexao = new ConnectionFactory().conexao();
	}

	// Insert
	public String inserir(FonteEnergia fonteEnergia) throws SQLException {

		PreparedStatement stmt = minhaConexao.prepareStatement("Insert into FonteEnergia values (?, ?, ?, ?, ?, ?)");

		stmt.setLong(1, fonteEnergia.getId());
		stmt.setString(2, fonteEnergia.getNome());
		stmt.setString(3, fonteEnergia.getTipo());
		stmt.setString(4, fonteEnergia.getDescricao());
		stmt.setInt(5, fonteEnergia.getImpactoAmbiental());
		stmt.setString(6, fonteEnergia.getImgUrl());

		stmt.execute();
		stmt.close();
		return "Fonte de Energia cadastrado com Sucesso!";
	}

	public FonteEnergia buscarFonteEnergiaPorId(Long id) throws SQLException {

		PreparedStatement stmt = minhaConexao.prepareStatement("SELECT * FROM FonteEnergia WHERE id = ?");
		stmt.setLong(1, id);
		ResultSet resposta = stmt.executeQuery();

		while (resposta.next()) {
			FonteEnergia fonteEnergia = new FonteEnergia();
			fonteEnergia.setId(resposta.getLong(1));
			fonteEnergia.setNome(resposta.getString(2));
			fonteEnergia.setTipo(resposta.getString(3));
			fonteEnergia.setDescricao(resposta.getString(4));
			fonteEnergia.setImpactoAmbiental(resposta.getInt(5));
			fonteEnergia.setImgUrl(resposta.getString(6));

	
			return fonteEnergia;
		}

		return null;

	}

	public List<FonteEnergia> buscarTodasFontesEnergia() throws SQLException {

		List<FonteEnergia> fontesEnergia = new ArrayList<FonteEnergia>();

		PreparedStatement stmt = minhaConexao.prepareStatement("SELECT * FROM FonteEnergia");

		ResultSet resposta = stmt.executeQuery();

		while (resposta.next()) {
			FonteEnergia fonteEnergia = new FonteEnergia();
			fonteEnergia.setId(resposta.getLong(1));
			fonteEnergia.setNome(resposta.getString(2));
			fonteEnergia.setTipo(resposta.getString(3));
			fonteEnergia.setDescricao(resposta.getString(4));
			fonteEnergia.setImpactoAmbiental(resposta.getInt(5));
			fonteEnergia.setImgUrl(resposta.getString(6));
			fontesEnergia.add(fonteEnergia);
		}

		return fontesEnergia;
	}

	public String atualizarFonteEnergia(FonteEnergia fonteEnergia) throws SQLException {

		PreparedStatement stmt = minhaConexao
				.prepareStatement("Update FonteEnergia SET nome = ?, tipo = ?, descricao = ?, impactoAmbiental = ?, imgUrl = ? where id = ?");
		
		stmt.setString(1, fonteEnergia.getNome());
		stmt.setString(2, fonteEnergia.getTipo());
		stmt.setString(3, fonteEnergia.getDescricao());
		stmt.setInt(4, fonteEnergia.getImpactoAmbiental());
		stmt.setString(5, fonteEnergia.getImgUrl());
		stmt.setLong(6, fonteEnergia.getId());
		
		
		
		stmt.executeUpdate();
		stmt.close();
		
		return "Fonte de Energia atualizado com Sucesso!";

	}

	public String deletarFonteEnergia(long id) throws SQLException {

		PreparedStatement stmt = minhaConexao.prepareStatement("Delete from FonteEnergia where id = ?");
		stmt.setLong(1, id);
		stmt.execute();
		stmt.close();

		return "Fonte de Energia deletado com Sucesso!";
	}
}
