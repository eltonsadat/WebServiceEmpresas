package br.edu.unyleya.elton.dao;

import br.edu.unyleya.elton.model.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO extends BaseDAO {
	public Empresa getEmpresaById(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from empresa where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Empresa c = createEmpresa(rs);
				rs.close();
				return c;
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<Empresa> findByName(String name) throws SQLException {
		List<Empresa> empresas = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from empresa where lower(nome) like ?");
			stmt.setString(1, "%" + name.toLowerCase() +"%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Empresa c = createEmpresa(rs);
				empresas.add(c);
			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return empresas;
	}

	public List<Empresa> findByTipo(String tipo) throws SQLException {
		List<Empresa> empresas = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from empresa where tipo = ?");
			stmt.setString(1, tipo);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Empresa c = createEmpresa(rs);
				empresas.add(c);
			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return empresas;
	}

	public List<Empresa> getEmpresas() throws SQLException {
		List<Empresa> empresas = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from empresa");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Empresa c = createEmpresa(rs);
				empresas.add(c);
			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return empresas;
	}

	public Empresa createEmpresa(ResultSet rs) throws SQLException {
		Empresa c = new Empresa();
		c.setId(rs.getLong("id"));
		c.setNome(rs.getString("nome"));
		c.setEndereco(rs.getString("endereco"));
		
		return c;
	}

	public void save(Empresa c) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (c.getId() == null) {
				stmt = conn
						.prepareStatement(
								"insert into empresa (nome,endereco) VALUES(?,?)",
								Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn
						.prepareStatement("update empresa set nome=?,endereco=? where id=?");
			}
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getEndereco());
			if (c.getId() != null) {
				// Update
				stmt.setLong(3, c.getId());
			}
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir a empresa");
			}
			// Se inseriu, ler o id auto incremento
			if (c.getId() == null) {
				Long id = getGeneratedId(stmt);
				c.setId(id);
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	// id gerado com o campo auto incremento
	public static Long getGeneratedId(Statement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			Long id = rs.getLong(1);
			return id;
		}
		return 0L;
	}

	public boolean delete(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("delete from empresa where id=?");
			stmt.setLong(1, id);
			int count = stmt.executeUpdate();
			boolean ok = count > 0;
			return ok;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
}
