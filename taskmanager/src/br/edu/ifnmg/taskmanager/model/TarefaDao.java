package br.edu.ifnmg.taskmanager.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import br.edu.ifnmg.taskmanager.controller.Banco;

public class TarefaDao extends Dao<Tarefa> {

	@Override
	public Long salvar(Tarefa o) {
		Long idResposta = 0L;

		try {
			Connection con = Banco.getConexao();
			PreparedStatement pstmt;

			if (o.getId() == 0L) {
				// Inserir um novo objeto
				pstmt = con.prepareStatement("INSERT INTO tarefa " //
						+ "(descricao, prioridade, progresso) " //
						+ "values " //
						+ "(?, ?, ?);");

				pstmt.setString(1, o.getDescricao());
				pstmt.setByte(2, o.getPrioridade());
				pstmt.setShort(3, o.getProgresso());

			} else {
				// Atualizar um objeto existente
				pstmt = con.prepareStatement("update tarefa " + "set descricao = ?, prioridade = ?, " //
						+ "progresso = ?, concluida = ?, conclusao = ?" //
						+ "where id = ?;");

				pstmt.setString(1, o.getDescricao());
				pstmt.setByte(2, o.getPrioridade());
				pstmt.setShort(3, o.getProgresso());
				pstmt.setBoolean(4, o.getConcluida());
				pstmt.setDate(5, new java.sql.Date(o.getConclusao().getTime()));
				pstmt.setLong(6, o.getId());
			}

			pstmt.executeUpdate();
			idResposta = o.getId() == 0 ? getUltimoId() : o.getId();
			JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");

			// Liberação de recursos
			pstmt.close();

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao salvar no banco!");
		}

		return idResposta;
	}

	@Override
	public List<Tarefa> buscarTodos() {
		Connection con = Banco.getConexao();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement("select * from tarefa;");
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
		System.err.println(con.toString());
		return realizarConsulta(pstmt);
	}

	@Override
	public Boolean  excluir(Tarefa o) {

		Boolean resposta = false;

		try {
			Connection con = Banco.getConexao();
			PreparedStatement pstmt;

			pstmt = con.prepareStatement("DELETE FROM tarefa WHERE id = ?;");
			pstmt.setLong(1, o.getId());

			pstmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Dado excluído com sucesso!");
			// Liberação de recursos
			// Está atuando precocemente no fechamento da conexão com o banco
			pstmt.close();
			con.close();

		} catch (SQLException sqlex) {
			System.out.println("Erro ao Excluir a Tarefa...\n" + sqlex);
		}

		return resposta;
	}

	public Boolean excluirId(Long id) {
		Connection con = Banco.getConexao();
		PreparedStatement pstmt = null;

		try {
			Tarefa o = new Tarefa();

			pstmt = con.prepareStatement("DELETE FROM tarefa WHERE id=?");
			//pstmt.setLong(1, o.getId());
			pstmt.setLong(1, id);

			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Dado excluído com sucesso!");
			
			// Liberação de recursos
			// Está atuando precocemente no fechamento da conexão com o banco
			pstmt.close();
			con.close();

			return true;
		} catch (SQLException sqlex) {
			System.out.println("Erro ao Excluir o Cliente...\n" + sqlex);
			return false;
		}
	}

	
	private List<Tarefa> realizarConsulta(PreparedStatement pstmt) {
		List<Tarefa> tarefas = new ArrayList<Tarefa>();

		try {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Date dataCriacao = null;
				Timestamp timestamp = rs.getTimestamp("criacao");
				if (timestamp != null) {
					dataCriacao = new Date(timestamp.getTime());
				}

				tarefas.add(
						new Tarefa(rs.getLong("id"), rs.getString("descricao"), dataCriacao, rs.getByte("prioridade"),
								rs.getShort("progresso"), rs.getBoolean("concluida"), rs.getDate("conclusao")));
			}

			rs.close();
			pstmt.close();

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		if (tarefas.isEmpty()) {
			return null;

		} else {
			return tarefas;
		}
	}

	@Override
	public Tarefa buscarPorId(Long id) {
		
		Connection con = Banco.getConexao();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Tarefa tarefa = new Tarefa();
		
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM tarefa WHERE id= ?;");
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				
				Date data = rs.getDate("criacao");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String dataTexto=sdf.format(data);
				java.util.Date dataUtil = new java.util.Date();
				try {
					dataUtil=new SimpleDateFormat("dd/MM/yyyy").parse(dataTexto);
				} catch (ParseException e) {
					System.out.println("deu erro na dao"+e);
				}
				tarefa.setCriacao(data);
				
				tarefa.setPrioridade(rs.getByte("progresso"));
				tarefa.setConcluida(rs.getBoolean("concluida"));
				
				Date dataConcl = rs.getDate("conclusao");
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
				String dataTexto2=sdf2.format(dataConcl);
				java.util.Date dataUtil2 = new java.util.Date();
				try {
					dataUtil2=new SimpleDateFormat("dd/MM/yyyy").parse(dataTexto2);
				} catch (ParseException e) {
					System.out.println("deu erro na dao"+e);
				}
				tarefa.setCriacao(dataConcl);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return tarefa;
	}

}
