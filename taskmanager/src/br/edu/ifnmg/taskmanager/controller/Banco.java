package br.edu.ifnmg.taskmanager.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
	
	private static Connection conexao;
	
	private static String usuario = "root";
	private static String senha = "root";
	
	public static final String MYSQLDRIVER = "com.mysql.cj.jdbc.Driver"; //"com.mysql.jdbc.Driver"; 
	
	private static final String MYSQLURL =  "jdbc:mysql://127.0.0.1:3306/tarefa?useTimezone=true&serverTimezone=UTC";
	
	//private static final String MYSQLURL =  "jdbc:mysql://127.0.0.1:3306/tarefa?useSSL=true"; // jdbc:mysql://localhost:port/bd_name?useTimezone=true&serverTimezone=UTC"
	
	public static Connection getConexao() {
		if(conexao == null) {
			
			try {
				Class.forName(MYSQLDRIVER);
				System.out.println("Configuração MySQL bem-sucedida");
				
				conexao = DriverManager.getConnection(MYSQLURL, getUsuario(),  getSenha());
				System.out.println("Conexão bem-sucedida");
				
			} catch (ClassNotFoundException cnfex) {
				cnfex.printStackTrace();

			} catch (SQLException sqlex) {
				sqlex.printStackTrace();

			}
		}
		
		return conexao;
	}
	

	public static String getUsuario() {
		return usuario;
	}

	public static void setUsuario(String usuario) {
		Banco.usuario = usuario;
	}

	public static String getSenha() {
		return senha;
	}

	public static void setSenha(String senha) {
		Banco.senha = senha;
	}
	
	

}
