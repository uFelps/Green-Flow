package br.com.fiap.services;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.fiap.dao.ProjetoDAO;
import br.com.fiap.exceptions.NotFoundException;
import br.com.fiap.model.Projeto;

public class ProjetoBO {

	ProjetoDAO projetoDAO = null;

	public ArrayList<Projeto> buscarTodosProjetos() throws ClassNotFoundException, SQLException {
		ProjetoDAO projetoDAO = new ProjetoDAO();
		return (ArrayList<Projeto>) projetoDAO.buscarTodosProjetos();
	}

	public Projeto buscarProjetoPorId(Long id) throws SQLException, ClassNotFoundException {
		ProjetoDAO projetoDAO = new ProjetoDAO();

		Projeto projeto = projetoDAO.buscarProjetoPorId(id);

		if (projeto == null) {
			throw new NotFoundException("Projeto não encontrado");
		}

		return projeto;

	}

	public void inserirProjeto(Projeto projeto) throws ClassNotFoundException, SQLException {

		ProjetoDAO projetoDAO = new ProjetoDAO();

		projeto.setPublicadoEm(Date.valueOf(LocalDate.now()));

		projetoDAO.inserir(projeto);

	}

	public String atualizarProjeto(long id, Projeto projeto) throws ClassNotFoundException, SQLException {

		ProjetoDAO projetoDAO = new ProjetoDAO();

		Projeto projetoExists = buscarProjetoPorId(id);

		projetoExists.setNome(projeto.getNome());
		projetoExists.setDescricao(projeto.getDescricao());
		projetoExists.setStatus(projeto.getStatus());

		return projetoDAO.atualizarProjeto(projetoExists);

	}
	
	public String deletarProjeto(long id) throws ClassNotFoundException, SQLException {
		ProjetoDAO projetoDAO = new ProjetoDAO();

		Projeto projetoExists = buscarProjetoPorId(id);
		
		return projetoDAO.deletarProjeto(id);
	}

}
