package br.edu.ifnmg.taskmanager.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.edu.ifnmg.taskmanager.controller.Banco;

public abstract class Dao<T> {
	public abstract Long salvar(T o);

	public abstract T buscarPorId(Long id);

	public abstract List<T> buscarTodos();

	public abstract Boolean excluir(T o);

	public static Long getUltimoId() {
		Long ultimoId = 0L;

		try {
			Connection con = Banco.getConexao();
			PreparedStatement pstmt;

			pstmt = con.prepareStatement("select last_insert_id();");

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				ultimoId = rs.getLong(1);
			}

			pstmt.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ultimoId;
	}
}