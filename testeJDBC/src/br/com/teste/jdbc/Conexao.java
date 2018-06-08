package br.com.teste.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
	public static String status = "Nao conectou..";
	
	
	public static Connection getConexaoMySQL() {
		Connection con = null;
		try {
			String driverName = "com.mysql.cj.jdbc.Driver"; 
			Class.forName(driverName);
			
			String serverName = "localhost";
			String myDatabase = "banco";
			String timezone = "?useTimezone=true&serverTimezone=UTC";
			String SSL = "&useSSL=false";
			String url = "jdbc:mysql://" + serverName + "/" + myDatabase + timezone + SSL;
			String user = "root";
			String password = "";
			con = DriverManager.getConnection(url, user, password);
			
			if (con != null) {
				status = "Conectado com sucesso";
			} else {
				status = "Falha na conexao";
			}
			return con;
		} catch (ClassNotFoundException e) {
			System.out.println("Driver nao encontrado: " + e.getMessage());
			return null;
		} catch (SQLException e) {
			System.out.println("N foi possivel conectar ao BD: " + e.getMessage());
			return null;
		}
	}
	
	public static String getStatus() {
		return status;
	}
	
	public static boolean fecharConexao() {
		try {
			getConexaoMySQL().close();
			return true;
		} catch(SQLException e) {
			System.out.println("erro: " + e.getMessage());
			return false;
		}
	}
	
	public static Connection reiniciarConexao() {
		fecharConexao();
		return getConexaoMySQL();	
	}
	
	public static void main(String args[]) {
//		Connection conn = getConexaoMySQL();
//		String query = "SELECT * FROM Cliente";
//		try {
//			java.sql.Statement st = conn.createStatement();
//			java.sql.ResultSet rs = st.executeQuery(query);
//			while (rs.next()) {
//				int idCliente = rs.getInt("idCliente");
//				String nome = rs.getString("nome");
//				String sobrenome = rs.getString("sobrenome");
//				System.out.printf("%s - %s - %s\n", idCliente, nome, sobrenome);
//			}
//			st.close();
//			
//		} catch(SQLException e) {
//			System.out.println("erro: " + e.getMessage());
//		}
//		if (fecharConexao()) System.out.println("conexao fechada.");
	}
}

