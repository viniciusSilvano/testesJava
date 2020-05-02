package br.com.testeJava.entity.profissoes;

import br.com.testeJava.entity.enuns.ProfissaoEnum;

public abstract class Profissao {
	
	public Profissao() {
		super();
	}

	public Profissao(String nome, int idade) {
		super();
		this.nome = nome;
		this.idade = idade;
	}

	public String nome;
	public int idade;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public abstract ProfissaoEnum getFuncao();
	
	public String apresentarProfissao() {
		return String.format( "Sou um(a) %s", this.getClass().getSimpleName());
	}
}
