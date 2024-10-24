package br.com.fuctura.sistemaGerenciadorDeVeiculosProjetoAluno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import aula2.dao.Usuario;
import aula2.dao.UsuarioDAO;
import aula2.utils.FabricaDeConexao;

public class Aplicacao {

	@SuppressWarnings("finally")
	public static int leiaInt(String num) {

		int n = 0;

		while (true) {
			try {

				n = Integer.valueOf(num);
				return n;
			} catch (Exception e) {
				System.out.println("Erro! Digite apenas números inteiros");
			}
		}
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int opc;

		while (true) {

			System.out.println("|ESCOLHA UMA OPÇÃO|");
			System.out.println("|-----------------|");
			System.out.println("|1-Inserir usuário|");
			System.out.println("|2-Listar Banco   |");
			System.out.println("|3-Listar Usuário |");
			System.out.println("|4-Alterar usuário|");
			System.out.println("|5-Excluir contato|");
			System.out.println("|6-Sair           |");

			System.out.println("Opção:");

			opc = scan.nextInt();

			if (opc == 1) {
				System.out.println("\n ######## INSERIR USUARIO ######\n");

				System.out.println("\nDigite seu cpf:");

				String cpf = scan.next();

				UsuarioDAO usuarioDAO = new UsuarioDAO();
				boolean existe;

				existe = usuarioDAO.ifExisteCpf(cpf);

				if (existe) {
					System.out.println("\nCpf já cadastrado na base de dados!");
				} else {
					usuarioDAO.inserirUsuario(cpf);
				}

			} else if (opc == 2) {
				System.out.println("\n###### LISTAR USUARIO #####\n");

				UsuarioDAO usuarioDAO = new UsuarioDAO();
				ArrayList<Usuario> usuarios = usuarioDAO.listarTodos();
				
				if(usuarios.isEmpty()) {
					System.out.println("Nenhum usuário encontrado!");
				}else {
					for(Usuario usuario: usuarios) {
						System.out.println(usuario);
					}
				}

			} else if (opc == 3) {
				System.out.println("\n##### LISTAR USUÁRIO ");
				System.out.println("\nDigite seu cpf:");
				String cpf = scan.next();

				UsuarioDAO usuarioDAO = new UsuarioDAO();
				boolean existe;

				existe = usuarioDAO.ifExisteCpf(cpf);

				if (!existe) {
					System.out.println("\nUsuário não encontrado na base de dados!");

				} else {
					usuarioDAO.listarUsuario(cpf);

				}

			} else if (opc == 4) {
				System.out.println("\n#### ALTERAR USUÁRIO ####\n");

				System.out.println("\nDigite seu cpf:");
				String cpf = scan.next();
				
				scan.nextLine();

				UsuarioDAO usuarioDAO = new UsuarioDAO();

				boolean existe = usuarioDAO.ifExisteCpf(cpf);

				if (!existe) {
					System.out.println("\n Cpf não encontrado na base de dados!");
				} else {
					usuarioDAO.atualizarUsuario(cpf);
				}

			} else if (opc == 5) {
				System.out.println("\n ### EXCLUIR USUÁRIO ####\n");

				System.out.println("\nDigite seu cpf:");
				String cpf = scan.next();

				UsuarioDAO usuarioDAO = new UsuarioDAO();
				boolean existe;

				existe = usuarioDAO.ifExisteCpf(cpf);

				if (!existe) {
					System.out.println("\nCpf não encontrado na base de dados!");

				} else {
					System.out.println("Certeza que deseja excluir o cpf? " + cpf + "[S/n]");
					String resp = scan.next();

					if (resp.equalsIgnoreCase("N")) {
						System.out.println("Tudo bem");

					} else if (resp.equalsIgnoreCase("S")) {
						usuarioDAO.excluirUsuario(cpf);

					} else {
						System.out.println("Opção inválida.");
					}

				}

			} else if (opc == 6) {
				System.out.println("SAINDO...");
				break;

			} else {
				System.out.println("Opção inválida, tente novamente!");
			}
		}
	}

}