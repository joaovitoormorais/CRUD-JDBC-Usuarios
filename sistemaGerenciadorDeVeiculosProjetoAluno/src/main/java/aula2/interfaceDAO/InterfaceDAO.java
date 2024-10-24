package aula2.interfaceDAO;

import java.util.ArrayList;

import aula2.dao.Usuario;

public interface InterfaceDAO {
	
	public void inserirUsuarioDAO(String cpf);
	public ArrayList<Usuario> listarTodos();
	public void listarUsuario(String cpf);
	public void atualizarUsuario(String cpf);
	public void excluirUsuario(String cpf);

}
