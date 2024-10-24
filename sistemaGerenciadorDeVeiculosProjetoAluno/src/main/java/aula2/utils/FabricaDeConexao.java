package aula2.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexao {
	
	public static  Connection getConnection() {
		
	//conexão
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String usuario = "postgres";
		String senha = "220504";
		
		try {
			
			Connection myConnection = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conectado com sucesso!");
			return myConnection;
		} catch(SQLException e) {
			e.printStackTrace();
		}	
		return null;
		
	}
	
}