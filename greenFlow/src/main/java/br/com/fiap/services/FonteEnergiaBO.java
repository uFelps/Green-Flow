package br.com.fiap.services;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.FonteEnergiaDAO;
import br.com.fiap.dao.ParceiroDAO;
import br.com.fiap.exceptions.NotFoundException;
import br.com.fiap.model.FonteEnergia;
import br.com.fiap.model.Parceiro;


public class FonteEnergiaBO {

	FonteEnergiaDAO fonteEnergiaDAO = null;

	public ArrayList<FonteEnergia> buscarTodasFontesEnergia() throws ClassNotFoundException, SQLException {
		FonteEnergiaDAO fonteEnergiaDAO = new FonteEnergiaDAO();
		return (ArrayList<FonteEnergia>) fonteEnergiaDAO.buscarTodasFontesEnergia();
	}

	public FonteEnergia buscarFonteEnergiaPorId(Long id) throws SQLException, ClassNotFoundException {
		FonteEnergiaDAO fonteEnergiaDAO = new FonteEnergiaDAO();

		FonteEnergia fonteEnergia = fonteEnergiaDAO.buscarFonteEnergiaPorId(id);

		if (fonteEnergia == null) {
			throw new NotFoundException("Fonte de Energia não encontrado");
		}

		return fonteEnergia;

	}

	public void inserirFonteEnergia(FonteEnergia fonteEnergia) throws ClassNotFoundException, SQLException {

		FonteEnergiaDAO fonteEnergiaDAO = new FonteEnergiaDAO();

		fonteEnergiaDAO.inserir(fonteEnergia);

	}

	public String atualizarFonteEnergia(long id, FonteEnergia fonteEnergia) throws ClassNotFoundException, SQLException {

		FonteEnergiaDAO fonteEnergiaDAO = new FonteEnergiaDAO();

		FonteEnergia fonteEnergiaExists = buscarFonteEnergiaPorId(id);

		fonteEnergiaExists.setNome(fonteEnergia.getNome());
		fonteEnergiaExists.setTipo(fonteEnergia.getTipo());
		fonteEnergiaExists.setDescricao(fonteEnergia.getDescricao());
		fonteEnergiaExists.setImpactoAmbiental(fonteEnergia.getImpactoAmbiental());
		fonteEnergiaExists.setImgUrl(fonteEnergia.getImgUrl());
		

		return fonteEnergiaDAO.atualizarFonteEnergia(fonteEnergiaExists);

	}
	
	public String deletarFonteEnergia(long id) throws ClassNotFoundException, SQLException {
		FonteEnergiaDAO fonteEnergiaDAO = new FonteEnergiaDAO();

		FonteEnergia fonteEnergiaExists = buscarFonteEnergiaPorId(id);
		
		return fonteEnergiaDAO.deletarFonteEnergia(id);
	}

}
