package aula2.dao;

public class Usuario {

	private String cpf;
	private int idade;
	private String nome;
	private double altura;
	private String  genero;
		
		public Usuario() {
			super();
			
		}
		
	public Usuario(String cpf, int idade, String nome,  double altura, String genero) {
			super();
			this.cpf = cpf;
			this.idade = idade;
			this.nome = nome;
			this.altura = altura;
			this.genero = genero;
		}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Override
	public String toString() {
		return "Usuario [cpf=" + cpf + ", idade=" + idade + ", nome=" + nome + ", altura=" + altura + ", genero="
				+ genero + "]";
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
}
