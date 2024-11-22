package br.com.fiap.services;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.ParceiroDAO;
import br.com.fiap.exceptions.NotFoundException;
import br.com.fiap.model.Parceiro;


public class ParceiroBO {

	ParceiroDAO parceiroDAO = null;

	public ArrayList<Parceiro> buscarTodosParceiros() throws ClassNotFoundException, SQLException {
		ParceiroDAO parceiroDAO = new ParceiroDAO();
		return (ArrayList<Parceiro>) parceiroDAO.buscarTodosParceiros();
	}

	public Parceiro buscarParceiroPorId(Long id) throws SQLException, ClassNotFoundException {
		ParceiroDAO parceiroDAO = new ParceiroDAO();

		Parceiro parceiro = parceiroDAO.buscarParceiroPorId(id);

		if (parceiro == null) {
			throw new NotFoundException("Parceiro não encontrado");
		}

		return parceiro;

	}

	public void inserirParceiro(Parceiro parceiro) throws ClassNotFoundException, SQLException {

		ParceiroDAO parceiroDAO = new ParceiroDAO();

		parceiroDAO.inserir(parceiro);

	}

	public String atualizarParceiro(long id, Parceiro parceiro) throws ClassNotFoundException, SQLException {

		ParceiroDAO parceiroDAO = new ParceiroDAO();

		Parceiro parceiroExists = buscarParceiroPorId(id);

		parceiroExists.setNome(parceiro.getNome());
		parceiroExists.setTipoOrganizacao(parceiro.getTipoOrganizacao());
		parceiroExists.setEmail(parceiro.getEmail());
		parceiroExists.setTelefone(parceiro.getTelefone());
		parceiroExists.setImgUrl(parceiro.getImgUrl());
		

		return parceiroDAO.atualizarParceiro(parceiroExists);

	}
	
	public String deletarParceiro(long id) throws ClassNotFoundException, SQLException {
		ParceiroDAO parceiroDAO = new ParceiroDAO();

		Parceiro parceiroExists = buscarParceiroPorId(id);
		
		return parceiroDAO.deletarParceiro(id);
	}

}
