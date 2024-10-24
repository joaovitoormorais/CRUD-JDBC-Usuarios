package aula2.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import aula2.interfaceDAO.InterfaceDAO;
import aula2.utils.FabricaDeConexao;

//DAO = Data Acess Object

public class UsuarioDAO implements InterfaceDAO {

	public void inserirUsuario(String cpf) {
		
		Scanner scan = new Scanner(System.in);
		PreparedStatement pstm;
		String url = "insert into usuario (cpf, idade, nome, altura, genero) values (?,?,?,?,?)";
		
		System.out.println("Digite sua idade:");
		int idade = scan.nextInt();
		
		System.out.println("Digite sua altura:");
		double altura = scan.nextDouble();
		scan.nextLine();
		
		System.out.println("Digite seu gênero(M/F):");
		String genero = scan.nextLine();
		
		System.out.println("Digite seu nome:");
		String nome = scan.nextLine();
		
		Usuario usuario = new Usuario(cpf, idade, nome,  altura, genero);
		
		try {
			pstm = FabricaDeConexao.getConnection().prepareStatement(url);
			pstm.setString(1, usuario.getCpf());
			pstm.setInt(2, usuario.getIdade());
			pstm.setString(3, usuario.getNome());
			pstm.setDouble(4, usuario.getAltura());
			pstm.setString(5, usuario.getGenero());
			
			
			pstm.execute();
			pstm.close();
			System.out.println("Usuário inserido com sucesso");
		}catch(Exception e) {
			System.out.println("Erro ao inserir usuário");
		}
	}
	
	public boolean ifExisteCpf(String cpf) {
		PreparedStatement pstm;
		ResultSet rs;
		boolean existe = false;
		
		String url = "select cpf from usuario where cpf = ?";
		
		try {
			pstm = FabricaDeConexao.getConnection().prepareStatement(url);
			pstm.setString(1, cpf);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				existe = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return existe;
	}
	
	@Override
		public ArrayList<Usuario> listarTodos() {
		PreparedStatement pstm;
		ResultSet rs;
		ArrayList<Usuario> usuarios = new ArrayList<>();
		
		String sql = "SELECT * FROM usuario";
		
		try {
			pstm = FabricaDeConexao.getConnection().prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				String cpf = rs.getString("cpf");
				int idade = rs.getInt("idade");
				String nome = rs.getString("nome");
				double altura = rs.getDouble("altura");
				String genero = rs.getString("genero");
				
				Usuario usuario = new Usuario(cpf, idade, nome, altura, genero);
				
				usuarios.add(usuario);
			}
			
			pstm.close();
		}catch(SQLException e) {
			System.out.println("erro ao listar usuário:" + e.getMessage());
		}	
		return usuarios;
	}
	
	public void listarUsuario(String cpf) {
		PreparedStatement pstm;
		
		ResultSet rs;
		
		 String url = "select * from usuario where cpf = ?";
		 
		 
		try {
			
			pstm = FabricaDeConexao.getConnection().prepareStatement(url);
			pstm.setString(1, cpf);
			rs = pstm.executeQuery();
			 
			 if(rs.next()) {
				 String cpf1 = rs.getString("cpf");
				 int idade = rs.getInt("idade");
				 String nome = rs.getString("nome");
				 double altura = rs.getDouble("altura");
				 String genero = rs.getString("genero");
				 
				 Usuario usuario = new Usuario(cpf, idade, nome, altura, genero);
				  System.out.println(usuario.toString());
				 
			 }
		 }catch(Exception e) {
			System.out.println("Erro ao listar usuaário!" + e.getMessage());
		 }
	}
	
	@Override
	public void excluirUsuario(String cpf) {
		PreparedStatement pstm;
		String url = "delete from usuario where cpf = ?";
		
		try {
			pstm = FabricaDeConexao.getConnection().prepareStatement(url);
			pstm.setString(1, cpf);
			pstm.execute();
			pstm.close();
			
			System.out.println("\nContato excluido com sucesso");
			
		}catch(Exception e) {
			System.out.println("\nErro ao excluir contato");
		}
	}

	@Override
	public void inserirUsuarioDAO(String cpf) {
		
		Scanner entrada = new Scanner(System.in);
		PreparedStatement pstm;
		
		String url = "insert into usuario (cpf, idade, nome, altura, genero) values (?,?,?,?,?)";
		
		System.out.println("Digite sua nome:");
		String nome  = entrada.nextLine();
		
		System.out.println("Digite sua idade:");
		int idade = entrada.nextInt();
		
		System.out.println("Digite sua altura:");
		double altura = entrada.nextDouble();
		
		System.out.println("Digite seu gênero [M/f]");
		String genero = entrada.nextLine();
		
		Usuario usuario = new Usuario(cpf, idade, nome, altura, genero);
		
		try {
			pstm = FabricaDeConexao.getConnection().prepareStatement(url);
			pstm.setString(1, usuario.getCpf());
			pstm.setInt(2, usuario.getIdade());
			pstm.setString(3, usuario.getNome());
			pstm.setDouble(3, usuario.getAltura());
			pstm.setString(4, usuario.getGenero());
			
			pstm.execute();
			pstm.close();
			
			System.out.println("Usuário inserido com sucesso!");
	}catch(Exception e) {
		System.out.println("Erro ao inserir usuário!" + e.getMessage());
	}
	}

	@Override
	public void atualizarUsuario(String cpf) {
		PreparedStatement pstm;
		String url = "update usuario set idade = ?, nome = ?, altura = ?, genero = ?,  WHERE cpf = ?";
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Digite seu nome:");
		String nome = entrada.nextLine();
		
		System.out.println("Digite sua idade:");
		int idade = entrada.nextInt();
		
		System.out.println("Digite sua altura:");
		double altura = entrada.nextDouble();
		
		System.out.println("Digite seu gênero [M/f]");
		String genero = entrada.nextLine();
		
		try {
			pstm = FabricaDeConexao.getConnection().prepareStatement(url);
			
			pstm.setInt(1, idade);
			pstm.setString(2, nome);
			pstm.setDouble(3, altura);
			pstm.setString(4, genero);
			pstm.setString(5, cpf);
			
			int rowsAffected = pstm.executeUpdate();
			pstm.close();
			
			if(rowsAffected > 0) {
				System.out.println("Usuário atualizado com sucesso!");
			}else {
				System.out.println("Nenhum usuário encontrado com o cpf fornecido!");
			}
			}catch(Exception e) {
				System.out.println("Erro ao atualizar usuário" + e.getMessage());
			}
	}
	
}