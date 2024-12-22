package br.edu.unyleya.elton.servico;

import br.edu.unyleya.elton.model.Empresa;
import br.edu.unyleya.elton.dao.EmpresaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaService {
	private EmpresaDAO db = new EmpresaDAO();

	// Lista todos os empresas do banco de dados
	public List<Empresa> getEmpresas() {
		try {
			List<Empresa> empresas = db.getEmpresas();
                    
			return empresas;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Empresa>();
		}
	}

	// Busca um empresa pelo id
	public Empresa getEmpresa(Long id) {
		try {
			return db.getEmpresaById(id);
		} catch (SQLException e) {
			return null;
		}
	}

	// Deleta o empresa pelo id
	public boolean delete(Long id) {
		try {
			return db.delete(id);
		} catch (SQLException e) {
			return false;
		}
	}

	// Salva ou atualiza o empresa
	public boolean save(Empresa empresa) {
		try {
			db.save(empresa);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	// Busca o empresa pelo nome
	public List<Empresa> findByName(String name) {
		try {
			return db.findByName(name);
		} catch (SQLException e) {
			return null;
		}
	}


}
