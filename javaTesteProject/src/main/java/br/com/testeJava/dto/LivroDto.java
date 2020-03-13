package br.com.testeJava.dto;

public class LivroDto implements IDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6958223360419716648L;
	
	public LivroDto(String nome) {
		super();
		this.nome = nome;
	}

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
